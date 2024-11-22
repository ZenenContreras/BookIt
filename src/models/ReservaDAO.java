package models;

import controllers.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // Método para insertar una nueva reserva en la base de datos
    public void insertarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (cantidad_entradas, usuarioid, eventoid, fecha_reserva, estado_pagoid) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getCantidad());
            stmt.setInt(2, reserva.getUsuarioId());
            stmt.setInt(3, reserva.getEventoId());
            stmt.setDate(4, reserva.getFecha());
            stmt.setInt(5, reserva.getEstado()); // Usamos int para el estado
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar reserva", e);
        }
    }

    // Método para obtener todas las reservas de un usuario
    public List<Reserva> obtenerReservasPorUsuario(int usuarioId) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE usuarioid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservas.add(new Reserva(
                            rs.getInt("id"),
                            rs.getInt("cantidad_entradas"),
                            rs.getInt("usuarioid"),
                            rs.getInt("eventoid"),
                            rs.getDate("fecha_reserva"),
                            rs.getInt("estado_pagoid") // Estado como entero
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener reservas por usuario", e);
        }
        return reservas;
    }

    // Método para actualizar una reserva existente
    public void actualizarReserva(Reserva reserva) {
        String sql = "UPDATE reserva SET cantidad_entradas = ?, usuarioid = ?, eventoid = ?, fecha_reserva = ?, estado_pagoid = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getCantidad());
            stmt.setInt(2, reserva.getUsuarioId());
            stmt.setInt(3, reserva.getEventoId());
            stmt.setDate(4, reserva.getFecha());
            stmt.setInt(5, reserva.getEstado()); // Usamos int para el estado
            stmt.setInt(6, reserva.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar reserva", e);
        }
    }

    // Método para eliminar una reserva
    public void eliminarReserva(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar reserva", e);
        }
    }

    // Método para obtener una reserva por ID
    public Reserva obtenerReservaPorId(int id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reserva(
                            rs.getInt("id"),
                            rs.getInt("cantidad_entradas"),
                            rs.getInt("usuarioid"),
                            rs.getInt("eventoid"),
                            rs.getDate("fecha_reserva"),
                            rs.getInt("estado_pagoid") // Estado como entero
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener reserva por ID", e);
        }
        return null;
    }

    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("id"),
                        rs.getInt("cantidad_entradas"),
                        rs.getInt("usuarioid"),
                        rs.getInt("eventoid"),
                        rs.getDate("fecha_reserva"),
                        rs.getInt("estado_pagoid")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las reservas", e);
        }
        return reservas;
    }

}
