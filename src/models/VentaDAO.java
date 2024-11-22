package models;

import controllers.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM pago";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setMonto(rs.getInt("monto"));
                venta.setFechaPago(rs.getDate("fecha_pago"));
                venta.setReservaId(rs.getInt("reservaid"));
                venta.setMetodoPagoId(rs.getInt("metodo_pagoid"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ventas", e);
        }
        return ventas;
    }

    public List<Venta> obtenerInformeVentas() {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM pago"; // Ajusta la consulta según los datos que deseas obtener
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setMonto(rs.getInt("monto"));
                venta.setFechaPago(rs.getDate("fecha_pago"));
                venta.setReservaId(rs.getInt("reservaid"));
                venta.setMetodoPagoId(rs.getInt("metodo_pagoid"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el informe de ventas", e);
        }
        return ventas;
    }


    public void agregarVenta(Venta venta) {
        String query = "INSERT INTO pago (monto, fecha_pago, reservaid, metodo_pagoid) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, venta.getMonto());
            stmt.setDate(2, new Date(venta.getFechaPago().getTime()));
            stmt.setInt(3, venta.getReservaId());
            stmt.setInt(4, venta.getMetodoPagoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar venta", e);
        }
    }

    public void eliminarVenta(int ventaId) {
        String query = "DELETE FROM pago WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ventaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar venta", e);
        }
    }

    public List<Venta> obtenerVentasPorReserva(int reservaId) {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM pago WHERE reservaid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setId(rs.getInt("id"));
                    venta.setMonto(rs.getInt("monto"));
                    venta.setFechaPago(rs.getDate("fecha_pago"));
                    venta.setReservaId(rs.getInt("reservaid"));
                    venta.setMetodoPagoId(rs.getInt("metodo_pagoid"));
                    ventas.add(venta);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ventas por reserva", e);
        }
        return ventas;
    }
}
