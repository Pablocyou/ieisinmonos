package com.teamadresinmonos.ieifinal.Extractor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamadresinmonos.ieifinal.Util.mariadbConnect;
import com.teamadresinmonos.ieifinal.Wrapper.WrapperMUR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ExtractorMUR {
    public static int dunk(String filename) throws Exception{
        System.out.println("Empezando dunk de MUR");
        Connection connection = mariadbConnect.mdbconn();
        List<String> lista = WrapperMUR.kebab(filename);
        int insertados = 0;

        while(lista.size()>0) {
            try {
                String current = lista.remove(0);
                JsonObject jsonObject = JsonParser.parseString(current).getAsJsonObject();
                String nombre = jsonObject.get("dencen").getAsString();
                String naturalesa = jsonObject.get("titularidad").getAsString();
                switch (naturalesa){
                    case "P":
                        naturalesa = "PUBLICO";
                        break;
                    case "C":
                        naturalesa = "CONCERTADO";
                        break;
                    case "N":
                        naturalesa = "PRIVADO";
                        break;
                    default:
                        naturalesa = "OTROS";
                }

                String adressa = jsonObject.get("domcen").getAsString();
                String codiPostal="";
                try {
                    codiPostal = "" + (jsonObject.get("cpcen").getAsInt());
                }catch(Exception e){codiPostal="";}

                double longitud;
                double latitud;

                //si una de las 2 lat o lon no esta incluida en el archivo, se comporta igual que si no hubiera ninguna coord
                if (jsonObject.get("geo-referencia") == null || (jsonObject.get("geo-referencia") != null && ((jsonObject.get("geo-referencia").getAsJsonObject()).get("lon") == null || (jsonObject.get("geo-referencia").getAsJsonObject()).get("lat") == null ))){
                    latitud = 0.0;
                    longitud = 0.0;
                }
                else {
                    longitud = (jsonObject.get("geo-referencia").getAsJsonObject()).get("lon").getAsDouble();
                    latitud = (jsonObject.get("geo-referencia").getAsJsonObject()).get("lat").getAsDouble();
                }

                String descripcion = jsonObject.get("denLarga").getAsString();

                String nombrelocalidad = jsonObject.get("loccen").getAsString();
                String telefono = jsonObject.get("telcen").getAsString();
                String codigolocalidad = "MURCIA" + jsonObject.get("cpcen").getAsString();
                String nombreprovincia = "MURCIA";
                String codigoprovincia = "MURCIA";

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
                     telefono = ?, descripcion = ?, localidad = ?, comunidad = ?
""");
                statement3.setString(1,nombre);
                statement3.setString(2,naturalesa);
                statement3.setString(3,adressa);
                statement3.setString(4,codiPostal);
                statement3.setDouble(5,longitud);
                statement3.setDouble(6,latitud);
                statement3.setString(7,telefono);
                statement3.setString(8,descripcion);
                statement3.setString(9,codigolocalidad);
                statement3.setString(10, "MUR");
                int res = statement3.executeUpdate();
                if(res == 1)
                    insertados++;
            }
            catch(Exception e){System.out.println("EXCEPTION EXTRACTOR MUR: " + e);}
        }

        return insertados;
    }
}
