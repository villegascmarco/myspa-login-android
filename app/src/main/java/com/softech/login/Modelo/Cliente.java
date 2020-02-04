package com.softech.login.Modelo;

public final class Cliente extends Persona {

    private int idCliente;
    private String numeroUnico;
    private String correo;
    private int estatus;
    private Usuario usuario;

    public Cliente() {
    }

    //Ya tiene el numero unico
    public Cliente(int idCliente, String numeroUnico, String correo, int estatus, Usuario usuario, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String genero, String domicilio, String telefono, String rfc) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, rfc);
        this.idCliente = idCliente;
        this.numeroUnico = numeroUnico;
        this.correo = correo;
        this.estatus = estatus;
        this.usuario = usuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}