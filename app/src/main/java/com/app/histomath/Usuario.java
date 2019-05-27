package com.app.histomath;

public class Usuario {

    private String userName;
    private String correo;

    public Usuario(String userName, String correo) {
        this.userName = userName;
        this.correo = correo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
