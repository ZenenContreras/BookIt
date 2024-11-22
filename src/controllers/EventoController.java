package controllers;

import models.Evento;
import models.EventoDAO;

import java.util.List;

public class EventoController {
    private final EventoDAO eventoDAO;

    public EventoController() {
        this.eventoDAO = new EventoDAO();
    }

    // Método para obtener todos los eventos (usado por administrador y cliente)
    public List<Evento> obtenerEventos() {
        return eventoDAO.obtenerEventos(); // Método en EventoDAO que recupera los eventos de la base de datos
    }


    // Método para filtrar eventos por fecha, tipo y disponibilidad (exclusivo para cliente)
    public List<Evento> filtrarEventos(String fechaDesde, String fechaHasta, String tipo, String disponibilidad) {
        return eventoDAO.filtrarEventos(fechaDesde, fechaHasta, tipo, disponibilidad);
    }


    // Método para obtener un evento específico por su ID (usado por administrador y cliente)
    public Evento obtenerEvento(int id) {
        return eventoDAO.obtenerEvento(id);
    }

    // Métodos exclusivos del administrador
    public void agregarEvento(Evento evento) {
        eventoDAO.agregarEvento(evento);
    }

    public void actualizarEvento(Evento evento) {
        eventoDAO.actualizarEvento(evento);
    }

    public void eliminarEvento(int id) {
        eventoDAO.eliminarEvento(id);
    }
}
