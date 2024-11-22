package controllers;

import models.Reserva;
import models.ReservaDAO;
import models.Venta;
import models.VentaDAO;

import java.util.List;

public class ReservaController {
    private final ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public void realizarReserva(Reserva reserva) {
        reserva.setEstado(1); // Estado inicial como "Aceptada"
        reservaDAO.insertarReserva(reserva);
    }


    public Reserva obtenerReservaPorId(int reservaId) {
        return reservaDAO.obtenerReservaPorId(reservaId);  // Llamamos al DAO para obtener la reserva desde la base de datos
    }


    public void actualizarReserva(Reserva reserva) {
        reservaDAO.actualizarReserva(reserva);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaDAO.obtenerTodasLasReservas();
    }

    public void registrarVentaDesdeReserva(Reserva reserva) {
        VentaDAO ventaDAO = new VentaDAO();
        // Crea una nueva venta a partir de la reserva aceptada
        Venta venta = new Venta(
                0, // ID se autogenera
                reserva.getCantidad(),
                reserva.getUsuarioId(),
                reserva.getEventoId(),
                new java.sql.Date(System.currentTimeMillis())// Fecha actual
        );
        ventaDAO.insertarVenta(venta); // Registrar la venta
    }



}
