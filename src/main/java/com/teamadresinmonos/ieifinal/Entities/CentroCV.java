package com.teamadresinmonos.ieifinal.Entities;

public class CentroCV {

    private String codigo;
    private String denominacionGenericaEs;
    private String denominacionGenericaVal;
    private String denominacionEspecifica;
    private String denominacion;
    private String regimen;
    private String tipoVia;
    private String direccion;
    private String numero;
    private String codigoPostal;
    private String localidad;
    private String provincia;
    private String telefono;
    private String fax;
    private String codEdificacion;
    private String titularidad;
    private String cif;
    private String comarca;
    private String urlEs;
    private String urlVa;

    public CentroCV(String codigo, String denominacionGenericaEs, String denominacionGenericaVal, String denominacionEspecifica, String denominacion, String regimen, String tipoVia, String direccion, String numero, String codigoPostal, String localidad, String provincia, String telefono, String fax, String codEdificacion, String titularidad, String cif, String comarca, String urlEs, String urlVa) {
        this.codigo = codigo;
        this.denominacionGenericaEs = denominacionGenericaEs;
        this.denominacionGenericaVal = denominacionGenericaVal;
        this.denominacionEspecifica = denominacionEspecifica;
        this.denominacion = denominacion;
        this.regimen = regimen;
        this.tipoVia = tipoVia;
        this.direccion = direccion;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.fax = fax;
        this.codEdificacion = codEdificacion;
        this.titularidad = titularidad;
        this.cif = cif;
        this.comarca = comarca;
        this.urlEs = urlEs;
        this.urlVa = urlVa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacionGenericaEs() {
        return denominacionGenericaEs;
    }

    public void setDenominacionGenericaEs(String denominacionGenericaEs) {
        this.denominacionGenericaEs = denominacionGenericaEs;
    }

    public String getDenominacionGenericaVal() {
        return denominacionGenericaVal;
    }

    public void setDenominacionGenericaVal(String denominacionGenericaVal) {
        this.denominacionGenericaVal = denominacionGenericaVal;
    }

    public String getDenominacionEspecifica() {
        return denominacionEspecifica;
    }

    public void setDenominacionEspecifica(String denominacionEspecifica) {
        this.denominacionEspecifica = denominacionEspecifica;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCodEdificacion() {
        return codEdificacion;
    }

    public void setCodEdificacion(String codEdificacion) {
        this.codEdificacion = codEdificacion;
    }

    public String getTitularidad() {
        return titularidad;
    }

    public void setTitularidad(String titularidad) {
        this.titularidad = titularidad;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getUrlEs() {
        return urlEs;
    }

    public void setUrlEs(String urlEs) {
        this.urlEs = urlEs;
    }

    public String getUrlVa() {
        return urlVa;
    }

    public void setUrlVa(String urlVa) {
        this.urlVa = urlVa;
    }
}
