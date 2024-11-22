package controllers;

import models.Reserva;
import models.Venta;
import models.VentaDAO;

import java.util.List;

public class VentaController {
    private final VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public List<Venta> obtenerTodasLasVentas() {
        VentaDAO ventaDAO = new VentaDAO();
        return ventaDAO.obtenerTodasLasVentas();
    }

    // En VentaController
    public void registrarVentaDesdeReserva(Reserva reserva) {
        Venta venta = new Venta(
                0,  // ID, se asume que se autogenera
                reserva.getCantidad(),  // Cantidad de entradas
                reserva.getUsuarioId(),
                reserva.getEventoId(),
                new java.sql.Date(System.currentTimeMillis()) // Fecha de la venta
        );
        VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.insertarVenta(venta);
    }


}
