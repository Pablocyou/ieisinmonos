package com.teamadresinmonos.ieifinal.Entities.BaseDatos;

import java.util.Objects;

public class ProvinciaBD {

    /**
     * Clase que representa una Provincia según está en la BD
     * */

    String codigo;
    String nombre;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public ProvinciaBD(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.codigo, ((ProvinciaBD) obj).getCodigo()) &&
                Objects.equals(this.nombre, ((ProvinciaBD) obj).getNombre());
    }
}
