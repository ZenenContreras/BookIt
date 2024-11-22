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
}
