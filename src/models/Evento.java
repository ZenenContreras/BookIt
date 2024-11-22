package models;

import java.util.Date;

public class Evento {
    private int id;
    private String nombre;
    private int disponibilidad;
    private Date fecha;
    private String descripcion;
    private int capacidad;

    // Constructor completo
    public Evento(int id, String nombre, int disponibilidad, Date fecha, String descripcion, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
