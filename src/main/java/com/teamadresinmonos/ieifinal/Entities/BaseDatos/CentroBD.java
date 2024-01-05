package com.teamadresinmonos.ieifinal.Entities.BaseDatos;

import java.util.Objects;

public class CentroBD {
    /**
     * Clase que representa un Centro según está en la BD
     * */
    String nombre;
    String tipo;
    String direccion;
    int codigoPostal;
    double longitud;
    double latitud;
    String telefono;
    String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLocalidad() {
        return localidad;
    }

    String localidad;

    public CentroBD(String nombre, String tipo, String direccion, int codigoPostal, double longitud, double latitud, String telefono, String descripcion, String localidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.longitud = longitud;
        this.latitud = latitud;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.tipo, ((CentroBD) obj).getTipo()) &&
                Objects.equals(this.direccion, ((CentroBD) obj).getDireccion()) &&
                Objects.equals(this.codigoPostal, ((CentroBD) obj).getCodigoPostal()) &&
                Objects.equals(this.longitud, ((CentroBD) obj).getLongitud()) &&
                Objects.equals(this.latitud, ((CentroBD) obj).getLatitud()) &&
                Objects.equals(this.telefono, ((CentroBD) obj).getTelefono()) &&
                Objects.equals(this.descripcion, ((CentroBD) obj).getDescripcion()) &&
                Objects.equals(this.localidad, ((CentroBD) obj).getLocalidad()) &&
                Objects.equals(this.nombre, ((CentroBD) obj).getNombre());
    }
}
