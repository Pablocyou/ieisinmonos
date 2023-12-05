package com.teamadresinmonos.ieifinal.Entities;

public class CentroCAT {

    private String codiCentre;
    private String denominaciCompleta;
    private String nomNaturalesa;
    private String nomTitularitat;
    private String adreca;
    private String codiPostal;
    private String nomComarca;
    private String codiComarca;
    private String codiMunicipi5Digits;
    private String codiMunicipi6Digits;
    private String nomMunicipi;
    private String codiDistricteMunicipal;
    private double coordenadesUtmX;
    private double coordenadesUtmY;
    private double coordenadesGeoX;
    private double coordenadesGeoY;
    private String estudis;
    private String georeferencia;

    // Constructor, Getters and Setters


    public CentroCAT(){}

    public CentroCAT(String codiCentre, String denominaciCompleta, String nomNaturalesa, String nomTitularitat, String adreca, String codiPostal, String nomComarca, String codiComarca, String codiMunicipi5Digits, String codiMunicipi6Digits, String nomMunicipi, String codiDistricteMunicipal, double coordenadesUtmX, double coordenadesUtmY, double coordenadesGeoX, double coordenadesGeoY, String estudis, String georeferencia) {
        this.codiCentre = codiCentre;
        this.denominaciCompleta = denominaciCompleta;
        this.nomNaturalesa = nomNaturalesa;
        this.nomTitularitat = nomTitularitat;
        this.adreca = adreca;
        this.codiPostal = codiPostal;
        this.nomComarca = nomComarca;
        this.codiComarca = codiComarca;
        this.codiMunicipi5Digits = codiMunicipi5Digits;
        this.codiMunicipi6Digits = codiMunicipi6Digits;
        this.nomMunicipi = nomMunicipi;
        this.codiDistricteMunicipal = codiDistricteMunicipal;
        this.coordenadesUtmX = coordenadesUtmX;
        this.coordenadesUtmY = coordenadesUtmY;
        this.coordenadesGeoX = coordenadesGeoX;
        this.coordenadesGeoY = coordenadesGeoY;
        this.estudis = estudis;
        this.georeferencia = georeferencia;
    }
    public String getCodiCentre() {
        return codiCentre;
    }

    public void setCodiCentre(String codiCentre) {
        this.codiCentre = codiCentre;
    }

    public String getDenominaciCompleta() {
        return denominaciCompleta;
    }

    public void setDenominaciCompleta(String denominaciCompleta) {
        this.denominaciCompleta = denominaciCompleta;
    }

    public String getNomNaturalesa() {
        return nomNaturalesa;
    }

    public void setNomNaturalesa(String nomNaturalesa) {
        this.nomNaturalesa = nomNaturalesa;
    }

    public String getNomTitularitat() {
        return nomTitularitat;
    }

    public void setNomTitularitat(String nomTitularitat) {
        this.nomTitularitat = nomTitularitat;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(String codiPostal) {
        this.codiPostal = codiPostal;
    }

    public String getNomComarca() {
        return nomComarca;
    }

    public void setNomComarca(String nomComarca) {
        this.nomComarca = nomComarca;
    }

    public String getCodiComarca() {
        return codiComarca;
    }

    public void setCodiComarca(String codiComarca) {
        this.codiComarca = codiComarca;
    }

    public String getCodiMunicipi5Digits() {
        return codiMunicipi5Digits;
    }

    public void setCodiMunicipi5Digits(String codiMunicipi5Digits) {
        this.codiMunicipi5Digits = codiMunicipi5Digits;
    }

    public String getCodiMunicipi6Digits() {
        return codiMunicipi6Digits;
    }

    public void setCodiMunicipi6Digits(String codiMunicipi6Digits) {
        this.codiMunicipi6Digits = codiMunicipi6Digits;
    }

    public String getNomMunicipi() {
        return nomMunicipi;
    }

    public void setNomMunicipi(String nomMunicipi) {
        this.nomMunicipi = nomMunicipi;
    }

    public String getCodiDistricteMunicipal() {
        return codiDistricteMunicipal;
    }

    public void setCodiDistricteMunicipal(String codiDistricteMunicipal) {
        this.codiDistricteMunicipal = codiDistricteMunicipal;
    }

    public double getCoordenadesUtmX() {
        return coordenadesUtmX;
    }

    public void setCoordenadesUtmX(double coordenadesUtmX) {
        this.coordenadesUtmX = coordenadesUtmX;
    }

    public double getCoordenadesUtmY() {
        return coordenadesUtmY;
    }

    public void setCoordenadesUtmY(double coordenadesUtmY) {
        this.coordenadesUtmY = coordenadesUtmY;
    }

    public double getCoordenadesGeoX() {
        return coordenadesGeoX;
    }

    public void setCoordenadesGeoX(double coordenadesGeoX) {
        this.coordenadesGeoX = coordenadesGeoX;
    }

    public double getCoordenadesGeoY() {
        return coordenadesGeoY;
    }

    public void setCoordenadesGeoY(double coordenadesGeoY) {
        this.coordenadesGeoY = coordenadesGeoY;
    }

    public String getEstudis() {
        return estudis;
    }

    public void setEstudis(String estudis) {
        this.estudis = estudis;
    }

    public String getGeoreferencia() {
        return georeferencia;
    }

    public void setGeoreferencia(String georeferencia) {
        this.georeferencia = georeferencia;
    }
}
