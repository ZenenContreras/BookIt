package models;

import controllers.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(String email, String password) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true si encontró el usuario
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al validar usuario", e);
        }
    }

    public String obtenerTipoUsuarioPorEmail(String email, String tableName, String columnName, String columnType) {
        String sql = "SELECT tipo_usuario.estado FROM " + tableName +
                " INNER JOIN tipo_usuario ON " + tableName + ".tipo_usuarioid = tipo_usuario.id " +
                "WHERE " + columnName + " = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("estado"); // Retorna el estado del tipo de usuario
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el tipo de usuario", e);
        }
        return null;
    }


    public void agregarUsuario(String nombre, String correo, String contraseña) {
        String query = "INSERT INTO usuario (nombre, correo, contraseña, tipo_usuarioid) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3, contraseña);
            stmt.setInt(4, 2); // Siempre asignamos 2 para "Cliente"
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar usuario", e);
        }
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Usuario(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("correo"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("tipo_usuarioid")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuario por email", e);
        }
        return null;
    }


}
