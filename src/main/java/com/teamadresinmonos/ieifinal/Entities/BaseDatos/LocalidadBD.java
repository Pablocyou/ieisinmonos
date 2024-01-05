package com.teamadresinmonos.ieifinal.Entities.BaseDatos;

import java.util.Objects;

public class LocalidadBD {

    /**
     * Clase que representa una Localidad según está en la BD
     * */

    String codigo;
    String nombre;
    String provincia;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public LocalidadBD(String codigo, String nombre, String provincia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.codigo, ((LocalidadBD) obj).getCodigo()) &&
               Objects.equals(this.provincia, ((LocalidadBD) obj).getProvincia()) &&
               Objects.equals(this.nombre, ((LocalidadBD) obj).getNombre());
    }
}
