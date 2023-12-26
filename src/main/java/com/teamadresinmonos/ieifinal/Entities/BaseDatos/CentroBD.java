package com.teamadresinmonos.ieifinal.Entities.BaseDatos;

public class CentroBD {
    String nombre;
    String tipo;
    String direccion;
    int codigoPostal;
    double longitud;
    double latitud;
    String telefono;
    String descripcion;
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
}
