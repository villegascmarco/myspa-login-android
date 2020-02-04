package com.softech.login.Modelo;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private String token;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {

        Timestamp key;
        String usuario;
        String contra;

        key = new Timestamp(System.currentTimeMillis());

        usuario = this.getNombreUsuario();

        contra = this.getContrasenia();

        this.token = (DigestUtils.sha256Hex(
                usuario + ";" + contra + ";" + key));

    }

}