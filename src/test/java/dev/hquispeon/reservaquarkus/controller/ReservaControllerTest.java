package dev.hquispeon.reservaquarkus.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import dev.hquispeon.reservaquarkus.model.Reserva;
import dev.hquispeon.reservaquarkus.service.ReservaService;

import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
public class ReservaControllerTest {
    ReservaService reservaService;
    ReservaController controller;

    @BeforeEach
    public void setup() {
        reservaService = Mockito.mock(ReservaService.class);
        controller = new ReservaController();
        controller.reservaService = reservaService;
    }

    @Test
    @DisplayName("Test listar reservas cuando no hay datos (mock)")
    public void testListarReserva_vacia_mock() {
        when(reservaService.listarTodas()).thenReturn(Collections.emptyList());

        Response response = controller.listar();
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Test lista la reserva 999999 no existe (mock)")
    public void testObtenerReservaPorId_fallido_mock() {
        when(reservaService.obtenerPorId(999999L)).thenReturn(null);

        Response response = controller.obtenerPorId(999999L);
        assertEquals(404, response.getStatus());
    }
    
    @Test
    @DisplayName("Test obtener reserva por ID exitoso (mock)")
    public void testObtenerReservaPorId_exitoso_mock() {
        Reserva reserva = new Reserva();
        reserva.nombreCliente = "Test User";
        reserva.fechaReserva = LocalDateTime.of(2025, 8, 10, 10, 0);
        reserva.descripcion = "Desc";
        reserva.restricciones = "None";

        when(reservaService.obtenerPorId(1L)).thenReturn(reserva);

        Response response = controller.obtenerPorId(1L);
        assertEquals(200, response.getStatus());
        Reserva result = (Reserva) response.getEntity();
        assertEquals("Test User", result.nombreCliente);
    }
}
