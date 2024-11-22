package controllers;

import models.Usuario;
import models.UsuarioDAO;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(); // Inicializamos el DAO
    }

    // Método para validar credenciales del usuario
    public boolean validarCredenciales(String email, String password) {
        return usuarioDAO.validarUsuario(email, password); // Llamada directa al DAO
    }

    // Método para obtener el tipo de usuario por email
    public String obtenerTipoUsuario(String email) {
        return usuarioDAO.obtenerTipoUsuarioPorEmail(email, "usuario", "correo", "tipo_usuarioid");
    }

    public void registrarUsuario(String nombre, String correo, String contraseña) {
        usuarioDAO.agregarUsuario(nombre, correo, contraseña);
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioDAO.obtenerUsuarioPorEmail(email);
    }



}
