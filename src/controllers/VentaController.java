package controllers;

import models.Venta;
import models.VentaDAO;

import java.util.List;

public class VentaController {
    private final VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public List<Venta> obtenerInformeVentas() {
        return ventaDAO.obtenerInformeVentas();
    }
}
