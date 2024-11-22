package controllers;

import models.Reserva;
import models.ReservaDAO;

import java.util.List;

public class ReservaController {
    private final ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public void realizarReserva(Reserva reserva) {
        reservaDAO.realizarReserva(reserva);
    }

    public List<Reserva> obtenerReservasPorCliente(int clienteId) {
        return reservaDAO.obtenerReservasPorCliente(clienteId);
    }

    public List<Reserva> obtenerReservasPendientes() {
        return reservaDAO.obtenerReservasPorEstado(1); // 1 = Pendiente
    }

    public void actualizarEstadoReserva(int reservaId, int nuevoEstado) {
        reservaDAO.actualizarEstadoReserva(reservaId, nuevoEstado);
    }
}
