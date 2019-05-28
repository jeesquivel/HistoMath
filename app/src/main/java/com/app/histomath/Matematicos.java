package com.app.histomath;

public class Matematicos {
    private String nombre,fNacimiento,fMuerte,descripcion,imagen;

    public Matematicos(){

    }

    public Matematicos(String nombre, String fNacimiento, String fMuerte, String descripcion, String imagen) {
        this.nombre = nombre;
        this.fNacimiento = fNacimiento;
        this.fMuerte = fMuerte;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getfMuerte() {
        return fMuerte;
    }

    public void setfMuerte(String fMuerte) {
        this.fMuerte = fMuerte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
