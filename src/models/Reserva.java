package models;

import java.sql.Date;

public class Reserva {
    private int id;
    private int cantidadEntradas;
    private int usuarioId;
    private int eventoId;
    private Date fechaReserva;
    private int estadoPagoId;

    public Reserva(int id, int cantidadEntradas, int usuarioId, int eventoId, Date fechaReserva, int estadoPagoId) {
        this.id = id;
        this.cantidadEntradas = cantidadEntradas;
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.fechaReserva = fechaReserva;
        this.estadoPagoId = estadoPagoId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCantidadEntradas() { return cantidadEntradas; }
    public void setCantidadEntradas(int cantidadEntradas) { this.cantidadEntradas = cantidadEntradas; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getEventoId() { return eventoId; }
    public void setEventoId(int eventoId) { this.eventoId = eventoId; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public int getEstadoPagoId() { return estadoPagoId; }
    public void setEstadoPagoId(int estadoPagoId) { this.estadoPagoId = estadoPagoId; }
}
