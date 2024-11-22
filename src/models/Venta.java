package models;

import java.sql.Date;

public class Venta {
    private int id;
    private int cantidad;
    private int usuarioId;
    private int eventoId;
    private Date fecha;

    public Venta(int id, int cantidad, int usuarioId, int eventoId, Date fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.fecha = fecha;
    }

    // Constructor para inserciones
    public Venta(int cantidad, int usuarioId, int eventoId, Date fecha) {
        this(0, cantidad, usuarioId, eventoId, fecha);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
