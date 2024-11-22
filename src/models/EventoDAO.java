package models;

import controllers.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    // Obtener todos los eventos
    public Evento obtenerEvento(int id) {
        String query = "SELECT * FROM evento WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evento(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("disponibilidad"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("descripcion"),
                            resultSet.getInt("capacidad")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener evento", e);
        }
        return null;
    }


    // Filtrar eventos
    public List<Evento> filtrarEventos(String fechaDesde, String fechaHasta, String tipo, String disponibilidad) {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento WHERE fecha >= ? AND fecha <= ? AND (tipo = ? OR ? = 'Todos') AND disponibilidad >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fechaDesde);
            stmt.setString(2, fechaHasta);
            stmt.setString(3, tipo);
            stmt.setString(4, tipo);
            stmt.setInt(5, Integer.parseInt(disponibilidad));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eventos.add(new Evento(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("disponibilidad"),
                            rs.getDate("fecha"),
                            rs.getString("descripcion"),
                            rs.getInt("capacidad")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al filtrar eventos", e);
        }
        return eventos;
    }


    // Agregar un evento
    public void agregarEvento(Evento evento) {
        String query = "INSERT INTO evento (nombre, disponibilidad, fecha, descripcion, capacidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, evento.getNombre());
            statement.setInt(2, evento.getDisponibilidad());
            statement.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            statement.setString(4, evento.getDescripcion());
            statement.setInt(5, evento.getCapacidad());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar evento", e);
        }
    }

    // Actualizar un evento
    public void actualizarEvento(Evento evento) {
        String query = "UPDATE evento SET nombre = ?, disponibilidad = ?, fecha = ?, descripcion = ?, capacidad = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, evento.getNombre());
            statement.setInt(2, evento.getDisponibilidad());
            statement.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            statement.setString(4, evento.getDescripcion());
            statement.setInt(5, evento.getCapacidad());
            statement.setInt(6, evento.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar evento", e);
        }
    }

    // Eliminar un evento
    public void eliminarEvento(int id) {
        String query = "DELETE FROM evento WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar evento", e);
        }
    }

    public List<Evento> obtenerEventos() {
        List<Evento> eventos = new ArrayList<>();
        String query = "SELECT * FROM evento";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Evento evento = new Evento(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("disponibilidad"),
                        resultSet.getDate("fecha"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("capacidad")
                );
                eventos.add(evento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener eventos", e);
        }
        return eventos;
    }

}
