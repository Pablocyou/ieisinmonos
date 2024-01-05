package com.teamadresinmonos.ieifinal.Entities;

import java.util.List;

public class CentroMUR {

    /**
     * Clase que representa un Centro de MUR
     * */


    private double s;
    private String codcen;
    private String dencen;
    private String denCorta;
    private String denLarga;
    private String tipo;
    private String tipo_centr;
    private String dengencen;
    private String titularidad;
    private String domcen;
    private String cpcen;
    private String loccen;
    private String muncen;
    private String telcen;
    private String fax;
    private String email;
    private String web;
    private String infantil;
    private String primaria;
    private String secundaria;
    private String bachillerato;
    private String aula_abierta;
    private String fp;
    private String educacion_especial;
    private String escuela_idiomas;
    private String fpmedio;
    private String fpsuperior;
    private String bilingue_ingles;
    private String bilingue_frances;
    private String bilingue_aleman;
    private String plurilingue;
    private String dual;
    private String distancia;
    private String comedor;
    private String transporte;
    private String desayuno;
    private String aulaMatinal;
    private String jornada_co;
    private String turnos_diu;
    private String turnos_mat;
    private String turnos_noc;
    private String turnos_ves;
    private String horarioIntegrado;
    private String presentacionCorta;
    private List<Image> imagenes;
    private GeoReferencia geoReferencia;

    // Inner class for 'imagenes'
    public static class Image {
        private String url;

        // Getters and setters
    }

    // Inner class for 'geo-referencia'
    public static class GeoReferencia {
        private double lat;
        private double lon;

        // Getters and setters
    }

}
