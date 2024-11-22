package models;

public class UsuarioSesion {
    private static UsuarioSesion instance;
    private Usuario usuario;

    private UsuarioSesion() {
    }

    public static UsuarioSesion getInstance() {
        if (instance == null) {
            instance = new UsuarioSesion();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return usuario != null ? usuario.getId() : -1;
    }

    public String getNombreUsuario() {
        return usuario != null ? usuario.getNombreUsuario() : "Usuario no autenticado";
    }

    public boolean esAdministrador() {
        return usuario != null && usuario.esAdministrador();
    }

    public boolean esCliente() {
        return usuario != null && usuario.esCliente();
    }

    public void cerrarSesion() {
        this.usuario = null;
    }

    @Override
    public String toString() {
        return "UsuarioSesion{usuario=" + (usuario != null ? usuario.toString() : "No autenticado") + '}';
    }
}
