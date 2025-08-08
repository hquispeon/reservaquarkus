package dev.hquispeon.reservaquarkus.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import dev.hquispeon.reservaquarkus.model.Reserva;
import dev.hquispeon.reservaquarkus.service.ReservaService;

@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaController {
	
    @Inject
    ReservaService reservaService;

    @POST
    @Transactional
    @Path("/grabar")
    public Response crear(Reserva reserva) {
        String errorMessage = "Error al ejecutar la creación, vuelva a intentar";
        try {
            Reserva creada = reservaService.crearReserva(reserva);

            if (creada != null && creada.isPersistent()) {
                return Response.ok(creada).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("error", errorMessage))
                        .build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", errorMessage, "details", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/consultar")
    public Response listar() {
        List<Reserva> reservas = reservaService.listarTodas();

        if (reservas == null || reservas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "No se encontraron datos"))
                    .build();
        }

        return Response.ok(reservas).build();
    }

    @GET
    @Path("/consultar/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
    		Reserva reserva = reservaService.obtenerPorId(id);

        if (reserva == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "No se encontraron datos"))
                    .build();
        }

        return Response.ok(reserva).build();
    }
    
    @DELETE
    @Path("/borrar/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        try {
            boolean eliminada = reservaService.eliminarReserva(id);

            if (!eliminada) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "No se encontraron datos"))
                        .build();
            }

            return Response.ok(Map.of("mensaje", "Reserva eliminada correctamente")).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", "Error al ejecutar la eliminación, vuelva a intentar"))
                    .build();
        }
    }
}
