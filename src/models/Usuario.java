package models;

public class Usuario {
    private int id; // Identificador único del usuario
    private String nombreUsuario; // Nombre del usuario
    private String correo; // Correo del usuario
    private String contraseña; // Contraseña del usuario
    private int tipoUsuarioId; // Tipo de usuario (referencia a la tabla tipo_usuario)

    // Constructor completo
    public Usuario(int id, String nombreUsuario, String correo, String contraseña, int tipoUsuarioId) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    // Constructor simplificado (sin ID)
    public Usuario(String nombreUsuario, String contraseña, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    // Método para validar si es administrador
    public boolean esAdministrador() {
        return tipoUsuarioId == 1; // Supongamos que 1 representa "Administrador"
    }

    public boolean esCliente() {
        return tipoUsuarioId == 2; // Supongamos que 2 representa "Cliente"
    }
}
