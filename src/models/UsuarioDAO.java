package models;

import controllers.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Validar usuario por correo y contraseña
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

    // Obtener tipo de usuario por correo
    public String obtenerTipoUsuarioPorEmail(String email) {
        String sql = "SELECT tu.estado " +
                "FROM usuario u " +
                "INNER JOIN tipo_usuario tu ON u.tipo_usuarioid = tu.id " +
                "WHERE u.correo = ?";
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

    // Agregar un nuevo usuario
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

    // Obtener un usuario por correo
    public Usuario obtenerUsuarioPorEmail(String email) {
        String sql = "SELECT id, nombre, correo, contraseña, tipo_usuarioid FROM usuario WHERE correo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String correo = resultSet.getString("correo");
                    String contraseña = resultSet.getString("contraseña");
                    int tipoUsuarioId = resultSet.getInt("tipo_usuarioid");

                    System.out.println("Datos recuperados: ");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Correo: " + correo);
                    System.out.println("Contraseña: " + contraseña);
                    System.out.println("Tipo Usuario ID: " + tipoUsuarioId);

                    return new Usuario(
                            id,
                            nombre,
                            correo,
                            contraseña,
                            Usuario.TipoUsuario.fromId(tipoUsuarioId)
                    );
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuario por email", e);
        }
        return null; // Devuelve null si no se encuentra el usuario
    }
}
