package models;

import controllers.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void realizarReserva(Reserva reserva) {
        String query = "INSERT INTO reserva (cantidad_entradas, usuario_id, evento_id, fecha_reserva, estado_pagoid) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reserva.getCantidadEntradas());
            statement.setInt(2, reserva.getUsuarioId());
            statement.setInt(3, reserva.getEventoId());
            statement.setDate(4, reserva.getFechaReserva());
            statement.setInt(5, reserva.getEstadoPagoId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al realizar la reserva", e);
        }
    }

    public List<Reserva> obtenerReservasPorCliente(int clienteId) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva WHERE usuario_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservas.add(new Reserva(
                        resultSet.getInt("id"),
                        resultSet.getInt("cantidad_entradas"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("evento_id"),
                        resultSet.getDate("fecha_reserva"),
                        resultSet.getInt("estado_pagoid")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener reservas por cliente", e);
        }
        return reservas;
    }

    public List<Reserva> obtenerReservasPorEstado(int estado) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva WHERE estado_pagoid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, estado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservas.add(new Reserva(
                        resultSet.getInt("id"),
                        resultSet.getInt("cantidad_entradas"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("evento_id"),
                        resultSet.getDate("fecha_reserva"),
                        resultSet.getInt("estado_pagoid")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener reservas por estado", e);
        }
        return reservas;
    }

    public void actualizarEstadoReserva(int reservaId, int nuevoEstado) {
        String query = "UPDATE reserva SET estado_pagoid = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuevoEstado);
            statement.setInt(2, reservaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de la reserva", e);
        }
    }
}
