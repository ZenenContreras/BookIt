package models;

import java.util.Base64;
import java.util.Objects;

public class Usuario {
    private int id; // Identificador único del usuario
    private String nombreUsuario; // Nombre del usuario
    private String correo; // Correo del usuario
    private String contraseña; // Contraseña cifrada del usuario
    private TipoUsuario tipoUsuario; // Tipo de usuario (Administrador, Cliente, etc.)

    // Enumeración para tipos de usuario
    public enum TipoUsuario {
        ADMINISTRADOR(1),
        CLIENTE(2);

        private final int id;

        TipoUsuario(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static TipoUsuario fromId(int id) {
            for (TipoUsuario tipo : TipoUsuario.values()) {
                if (tipo.getId() == id) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Tipo de usuario no válido: " + id);
        }
    }

    // Constructor completo
    public Usuario(int id, String nombreUsuario, String correo, String contraseña, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        setCorreo(correo); // Validación de correo
        setContraseña(contraseña); // Cifrado de contraseña
        this.tipoUsuario = tipoUsuario;
    }

    // Constructor simplificado (sin ID)
    public Usuario(String nombreUsuario, String contraseña, TipoUsuario tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        setContraseña(contraseña);
        this.tipoUsuario = tipoUsuario;
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
        if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.correo = correo;
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        if (contraseña == null || contraseña.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
        this.contraseña = cifrarContraseña(contraseña);
    }

    private String cifrarContraseña(String contraseña) {
        return Base64.getEncoder().encodeToString(contraseña.getBytes());
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Métodos para validar roles
    public boolean esAdministrador() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR;
    }

    public boolean esCliente() {
        return tipoUsuario == TipoUsuario.CLIENTE;
    }

    // Sobrescritura de métodos
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' + ", tipoUsuario=" + tipoUsuario + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
