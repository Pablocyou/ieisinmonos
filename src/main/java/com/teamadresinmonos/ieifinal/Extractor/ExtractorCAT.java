package com.teamadresinmonos.ieifinal.Extractor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamadresinmonos.ieifinal.Util.mariadbConnect;
import com.teamadresinmonos.ieifinal.Wrapper.WrapperCAT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ExtractorCAT {

    public static int dunk(String filename){
        System.out.println("Empezando dunk de CAT");
        Connection connection = mariadbConnect.mdbconn();
        List<String> lista = WrapperCAT.kebab(filename);
        int insertados = 0;

        while(lista.size()>0) {
            try {
                String current = lista.remove(0);
                    JsonObject jsonObject = JsonParser.parseString(current).getAsJsonObject();
                    String nombre = jsonObject.get("denominaciCompleta").getAsString();
                    String naturalesa = jsonObject.get("nomNaturalesa").getAsString();

                    if (naturalesa.equals("Privat")) {
                        naturalesa = "PRIVADO";
                    } else if (naturalesa.equals("Públic")) {
                        naturalesa = "PUBLICO";
                    }

                    String adressa = jsonObject.get("adreca").getAsString();
                    String codiPostal = jsonObject.get("codiPostal").getAsString();
                    double longitud = jsonObject.get("coordenadesGeoX").getAsDouble();
                    double latitud = jsonObject.get("coordenadesGeoY").getAsDouble();
                    String descripcion = "Estudis: " + jsonObject.get("estudis").getAsString();
                    String nombrelocalidad = jsonObject.get("nomMunicipi").getAsString();
                    String codigolocalidad = jsonObject.get("codiMunicipi6Digits").getAsString();
                    String nombreprovincia = jsonObject.get("nomComarca").getAsString();
                    String codigoprovincia = jsonObject.get("codiComarca").getAsString();

                    //Insertamos la provincia solo si no esta, si ya esta pasamos
                    PreparedStatement statement = connection.prepareStatement("""
                    INSERT IGNORE INTO provincia SET codigo = ?, nombre = ?
""");
                    statement.setString(1,codigoprovincia);
                    statement.setString(2,nombreprovincia);
                    statement.executeUpdate();

                    //Insertamos la localidad solo si no esta, si ya esta pasamos
                    PreparedStatement statement2 = connection.prepareStatement("""
                    INSERT IGNORE INTO localidad SET codigo = ?, nombre = ?, provincia = ? 
""");
                    statement2.setString(1,codigolocalidad);
                    statement2.setString(2,nombrelocalidad);
                    statement2.setString(3,codigoprovincia);
                    statement2.executeUpdate();

                    //Insertamos el centro
                    PreparedStatement statement3 = connection.prepareStatement("""
                    INSERT IGNORE INTO centro SET nombre = ?,
                     tipo = ?, direccion = ?, codigo_postal = ?, longitud = ?, latitud = ?,
                     telefono = null, descripcion = ?, localidad = ?
""");
                    statement3.setString(1,nombre);
                    statement3.setString(2,naturalesa);
                    statement3.setString(3,adressa);
                    statement3.setString(4,codiPostal);
                    statement3.setDouble(5,longitud);
                    statement3.setDouble(6,latitud);
                    statement3.setString(7,descripcion);
                    statement3.setString(8,codigolocalidad);
                    statement3.executeUpdate();
                    insertados++;
            }
            catch(Exception e){System.out.println("EXCEPTION EXTRACTOR CAT: " + e);}
        }
        return insertados;
    }
}
