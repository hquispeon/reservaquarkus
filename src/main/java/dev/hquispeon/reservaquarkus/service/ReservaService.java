package dev.hquispeon.reservaquarkus.service;

import dev.hquispeon.reservaquarkus.model.Reserva;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ReservaService {

    /**
     * Lista todas las reservas disponibles.
     * @return Lista de reservas.
     */
    public List<Reserva> listarTodas() {
        return Reserva.listAll();
    }

    /**
     * Busca una reserva por su ID.
     * @param id Identificador de la reserva.
     * @return Reserva encontrada o null si no existe.
     */
    public Reserva obtenerPorId(Long id) {
        return Reserva.findById(id);
    }

    /**
     * Crea una nueva reserva.
     * @param reserva Instancia de reserva a persistir.
     * @return Reserva persistida.
     */
    public Reserva crearReserva(Reserva reserva) {
        reserva.persist();
        return reserva;
    }

    /**
     * Elimina una reserva por su ID.
     * @param id Identificador de la reserva.
     * @return true si fue eliminada, false si no existía.
     */
    public boolean eliminarReserva(Long id) {
        Reserva reserva = Reserva.findById(id);
        if (reserva != null) {
            reserva.delete();
            return true;
        }
        return false;
    }

    /**
     * Actualiza una reserva existente.
     * @param id Identificador de la reserva.
     * @param nuevaReserva Datos nuevos.
     * @return Reserva actualizada o null si no existe.
     */
    public Reserva actualizarReserva(Long id, Reserva nuevaReserva) {
        Reserva existente = Reserva.findById(id);
        if (existente != null) {
            existente.nombreCliente = nuevaReserva.nombreCliente;
            existente.fechaReserva = nuevaReserva.fechaReserva;
            existente.descripcion = nuevaReserva.descripcion;
            existente.restricciones = nuevaReserva.restricciones;
            existente.persist();
            return existente;
        }
        return null;
    }
}