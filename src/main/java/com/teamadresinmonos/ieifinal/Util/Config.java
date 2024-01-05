package com.teamadresinmonos.ieifinal.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
public class Config {
    //Sí, he copiado esta clase entera del proyecto de PSW y no tengo ninguna clase de remordimiento
    /**
     * Clase para según en qué entorno estemos acceder a los archivos en una ruto u otra
     * Detecta si estamos en un Windows Server 2016 (IEI mode) o no (DEBUG)
     * La ruta por defecto para los archivos en Server es C:\ y C:\Data\
     * */

    Properties properties = new Properties();
    File file;
    //Si añades algún atributo, describe añadiendo a este string qué valores debe aceptar
    String comment =
            "#ARCHIVO DE CONFIG DE SERVER IEI\n" +
            "#MARIADB -> La URL del servidor de MariaDB\n" +
            "#MDBUSER -> El usuario para mariaDB\n" +
            "#MDBPASS -> La contraseña para mariaDB\n\n\n";
    public Config(){
        try {
            file = new File(getResourcesLocation() + "db.properties");
            System.out.println("db.PROPERTIES EN: " + file.getAbsolutePath());
            loadProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
            //SI ESTO DA UNA EXCEPCION ES PORQUE EL ARCHIVO NO EXISTE
        }

        //region DEFAULT
        if(getMdbPass() == null ){setMdbPass("adre1234");}
        if(getMdbUser() == null ){setMdbUser("root");}
        if(getMdbURL() == null ){setMdbURL("jdbc:mariadb://localhost:3306/mansana");}
        //endregion
    }
    /*
    Si añades más valores a guardar, recuerda hacer un get y set como los de abajo
    SI ALGUN GET O SET DA EXCEPCION ES PORQUE LA CLAVE (AKA "OWNNAME" O "MDBPASS" ETC) NO EXISTE
    */

    //region MARIADB
    public String getMdbPass(){
        return (String)properties.get("MDBPASS");
    }
    public void setMdbPass(String newPass){
        properties.setProperty("MDBPASS",""+newPass);
        try {
            saveProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getMdbUser(){
        return (String)properties.get("MDBUSER");
    }
    public void setMdbUser(String newUser){
        properties.setProperty("MDBUSER",""+newUser);
        try {
            saveProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getMdbURL(){
        return (String)properties.get("MARIADB");
    }
    public void setMdbURL(String newURL){
        properties.setProperty("MARIADB",""+newURL);
        try {
            saveProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region UTILS
    private void saveProperties() throws IOException
    {
        FileOutputStream fr = new FileOutputStream(file);
        properties.store(fr, comment);
        fr.close();
    }
    private void loadProperties()throws IOException
    {
        FileInputStream fi=new FileInputStream(file);
        properties.load(fi);
        fi.close();
    }
    public static Boolean inDebug(){
        if(Objects.equals(System.getProperty("os.name"), "Windows Server 2016")){return false;} else return true;
    }
    public static String getResourcesLocation(){if(inDebug()){return "src/main/resources/";}else return "C:\\";}

    public static String getDataLocation(){if(inDebug()){return "src/main/resources/Data/";}else return "C:\\Data\\";}

    //endregion

}
