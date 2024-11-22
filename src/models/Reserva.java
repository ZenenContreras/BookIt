package models;

import java.sql.Date;

public class Reserva {
    private int id;
    private int cantidad;
    private int usuarioId;
    private int eventoId;
    private Date fecha;
    private int estado; // Cambiado a int para reflejar el campo en la base de datos

    // Constructor completo
    public Reserva(int id, int cantidad, int usuarioId, int eventoId, Date fecha, int estado) {
        this.id = id;
        this.cantidad = cantidad;
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Constructor para nuevas reservas
    public Reserva(int cantidad, int usuarioId, int eventoId, Date fecha, int estado) {
        this(0, cantidad, usuarioId, eventoId, fecha, estado);
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", usuarioId=" + usuarioId +
                ", eventoId=" + eventoId +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}
