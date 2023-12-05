package com.teamadresinmonos.ieifinal.Wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamadresinmonos.ieifinal.Entities.CentroCV;
import com.teamadresinmonos.ieifinal.Util.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WrapperCV {

    static BufferedReader br = null;


    //viejos
    static String DENOMINACION ="";
    static String regimen="";
    static String direccion="";
    static String codigoPostal="";
    static String nombreLocalidad="";
    static String nombreProvincia="";
    static String telefono="";
    static String descripcion="";
    static Double longitud=0.0;
    static Double latitud=0.0;
    static String codigoLocalidad="";
    static String codigoProvincia="";

    //nuevos
    static String codigo="";
    static String DENOMINACION_GENERICA_ES = "";
    static String DENOMINACION_GENERICA_VAL = "";
    static String DENOMINACION_ESPECIFICA = "";
    static String tipoVia="";
    static String numero="";
    static String FAX ="";
    static String CODEDIFICACION="";
    static String CIF="";
    static String URLES="";
    static String URLVA="";
    static String TITULARIDAD="";
    static String COMARCA="";



    public static List<String> kebab(String filename){

        List<String> sol = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(Config.getDataLocation() + filename));
            ObjectMapper mapper = new ObjectMapper();
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] fields = line.split(";");
                int i=0;
                for (String a:fields) {
                    if(i == 0) codigo = a;
                    if(i == 1) DENOMINACION_GENERICA_ES = a;
                    if(i == 2) DENOMINACION_GENERICA_VAL = a;
                    if(i == 3) DENOMINACION_ESPECIFICA = a;
                    if(i == 4) DENOMINACION = a;
                    if(i == 5) regimen = a;
                    if(i == 6) tipoVia = a;
                    if(i == 7) direccion = a;
                    if(i == 8) numero =  a;
                    if(i == 9) codigoPostal = a;
                    try {
                        if (i == 10) nombreLocalidad = a;
                    }catch (Exception e){
                        nombreLocalidad = "";
                    }
                    try {
                        if (i == 11) nombreProvincia = a.substring(0, a.indexOf("/"));
                    }catch (Exception e) {nombreProvincia = "";}
                    try {
                        if (i == 12) telefono = a;
                    }catch(Exception e) {telefono = null;}

                    if(i == 13) FAX = a;
                    if(i == 14) CODEDIFICACION = a;
                    if(i == 15) TITULARIDAD = a;
                    if(i == 16) CIF =  a;
                    if(i == 17) COMARCA = a;
                    if(i == 18) URLES =  a;
                    if(i == 19) URLVA = a;
                    //hacer transf de csv a objeto
                    /*
                    CODIGO;                                 0
                    DENOMINACION_GENERICA_ES;               1
                    DENOMINACION_GENERICA_VAL;              2
                    DENOMINACION_ESPECIFICA;                3
                    DENOMINACION;                           4
                    REGIMEN;                                5
                    TIPO_VIA;                               6
                    DIRECCION;                              7
                    NUMERO;                                 8
                    CODIGO_POSTAL;                          9
                    LOCALIDAD;                              10
                    PROVINCIA;                              11
                    TELEFONO;                               12
                    FAX;                                    13
                    COD_EDIFICACION;                        14
                    TITULARIDAD;                            15
                    CIF;                                    16
                    COMARCA;                                17
                    URL_ES;                                 18
                    URL_VA                                  19
                    */
                    i++;
                }
                sol.add(mapper.writeValueAsString(new CentroCV(codigo,DENOMINACION_GENERICA_ES,DENOMINACION_GENERICA_VAL,
                        DENOMINACION_ESPECIFICA,DENOMINACION,regimen,tipoVia,direccion,numero,codigoPostal,
                        nombreLocalidad,nombreProvincia,telefono,FAX,CODEDIFICACION,TITULARIDAD,CIF,COMARCA,URLES,URLVA)));
                attreset();
                line = br.readLine();
            }
        }catch (Exception e){
            System.out.println("EXCEPCION EN WRAPPER CV: " + e);
        }
        return sol;

    }
    
    private static void attreset(){
        //viejos
        DENOMINACION ="";
        regimen="";
        direccion="";
        codigoPostal="";
        nombreLocalidad="";
        nombreProvincia="";
        telefono="";
        descripcion="";
        longitud=0.0;
        latitud=0.0;
        codigoLocalidad="";
        codigoProvincia="";

        //nuevos
        codigo="";
        DENOMINACION_GENERICA_ES = "";
        DENOMINACION_GENERICA_VAL = "";
        DENOMINACION_ESPECIFICA = "";
        DENOMINACION = "";
        tipoVia="";
        numero="";
        FAX ="";
        CODEDIFICACION="";
        CIF="";
        URLES="";
        URLVA="";
        TITULARIDAD="";
        COMARCA="";
    }
}
