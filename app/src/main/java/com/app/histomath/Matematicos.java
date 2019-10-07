package com.app.histomath;

import android.os.Parcel;
import android.os.Parcelable;

public class Matematicos implements Parcelable {
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

    protected Matematicos(Parcel in) {
        nombre = in.readString();
        fNacimiento = in.readString();
        fMuerte = in.readString();
        descripcion = in.readString();
        imagen = in.readString();
    }

    public static final Creator<Matematicos> CREATOR = new Creator<Matematicos>() {
        @Override
        public Matematicos createFromParcel(Parcel in) {
            return new Matematicos(in);
        }

        @Override
        public Matematicos[] newArray(int size) {
            return new Matematicos[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(fNacimiento);
        dest.writeString(fMuerte);
        dest.writeString(descripcion);
        dest.writeString(imagen);
    }
}
