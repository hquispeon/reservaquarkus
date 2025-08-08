package dev.hquispeon.reservaquarkus.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Reserva extends PanacheEntity {

    public String nombreCliente;
    public LocalDateTime fechaReserva;
    public String descripcion;
    public String restricciones;
}