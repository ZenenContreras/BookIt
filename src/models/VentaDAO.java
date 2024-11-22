package models;

import controllers.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {

    public void registrarVentaDesdeReserva(Reserva reserva) {
        Venta venta = new Venta(
                0, // Asume que el ID se genera automáticamente
                reserva.getCantidad(),
                reserva.getUsuarioId(),
                reserva.getEventoId(),
                new java.sql.Date(System.currentTimeMillis()) // Convertir a java.sql.Date
        );

        VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.insertarVenta(venta);
    }

    public void eliminarReserva(int reservaId) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar reserva", e);
        }
    }


    public List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ventas.add(new Venta(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getInt("usuarioid"),
                        rs.getInt("eventoid"),
                        rs.getDate("fecha")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las ventas", e);
        }

        return ventas;
    }

    // En VentaDAO
    public void insertarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (cantidad, usuarioid, eventoid, fecha) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getCantidad());
            stmt.setInt(2, venta.getUsuarioId());
            stmt.setInt(3, venta.getEventoId());
            stmt.setDate(4, venta.getFecha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar venta", e);
        }
    }




}
