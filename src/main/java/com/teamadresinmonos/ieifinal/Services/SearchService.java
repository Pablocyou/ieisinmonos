package com.teamadresinmonos.ieifinal.Services;

import com.teamadresinmonos.ieifinal.Entities.BaseDatos.CentroBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.LocalidadBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.ProvinciaBD;
import com.teamadresinmonos.ieifinal.Util.mariadbConnect;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private static Connection connection;

    //region centro
    public static List<CentroBD> searchCentro(String nombre, String tipo, String direccion, int codigoPostal,
                                              double longitud, double latitud, String telefono, String descripcion, String localidad, String comunidad){
        List<CentroBD> sol = new ArrayList<>();
        connection = mariadbConnect.mdbconn();

        if(!nombre.isEmpty() && !nombre.isBlank()){
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.nombre = ?
                """)) {
                sol = getCentroBDS(nombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!tipo.isEmpty() && !tipo.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.tipo = ?
                """)) {
                sol = getCentroBDS(tipo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!direccion.isEmpty() && !direccion.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.provincia = ?
                """)) {
                sol = getCentroBDS(direccion, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (codigoPostal != 0) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.codigo_postal = ?
                """)) {
                sol = getCentroBDS(codigoPostal + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (longitud != 69.420) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.longitud = ?
                """)) {
                sol = getCentroBDS(longitud + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (latitud != 69.420) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.latitud = ?
                """)) {
                sol = getCentroBDS(latitud + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!telefono.isEmpty() && !telefono.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.telefono = ?
                """)) {
                sol = getCentroBDS(telefono, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!descripcion.isEmpty() && !descripcion.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.descripcion = ?
                """)) {
                sol = getCentroBDS(descripcion, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!localidad.isEmpty() && !localidad.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.localidad = ?
                """)) {
                sol = getCentroBDS(localidad, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!comunidad.isEmpty() && !comunidad.isBlank()) {
            String comunidad2 = comunidad.replace(",", "','");
            String sql = "SELECT * FROM centro p WHERE p.comunidad IN ('" + comunidad2 + "')";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                sol = getCentroBDS("comunidad", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }
        return sol;
    }

    private static List<CentroBD> getCentroBDS(String input, List<CentroBD> sol, PreparedStatement statement) throws Exception {
        CentroBD item;
        List<CentroBD> sol2 = new ArrayList<>();
        if(input.equals("comunidad"))
            statement.setString(1, input);
        ResultSet resultSet = statement.executeQuery();

        if(!resultSet.next()){
            try {
                connection.close();
                mariadbConnect.connclosed();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return new ArrayList<>();} else {
            do {
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                String direccion = resultSet.getString("direccion");
                int cp = 0;
                try{
                cp = resultSet.getInt("codigo_postal");}catch(Exception ignored){}
                double longitud = resultSet.getDouble("longitud");
                double latitud = resultSet.getDouble("latitud");
                String telefono = resultSet.getString("telefono");
                String descripcion = resultSet.getString("descripcion");
                String localidad = resultSet.getString("localidad");
                item = new CentroBD(nombre, tipo, direccion, cp, longitud, latitud, telefono, descripcion, localidad);
                if(sol.contains(item) || sol.isEmpty())
                    sol2.add(item);
            } while (resultSet.next());
            if(sol2.isEmpty()) throw new Exception();
            return sol2;
        }
    }
    //endregion


    //region localidad
    public static List<LocalidadBD> searchLocalidad(String incodigo, String innombre, String inProvincia){
        List<LocalidadBD> sol = new ArrayList<>();
        connection = mariadbConnect.mdbconn();

        if(!incodigo.isEmpty() && !incodigo.isBlank()){
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM localidad p
                    WHERE p.codigo = ?
                """)) {
                sol = getLocalidadBDS(incodigo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!innombre.isEmpty() && !innombre.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM localidad p
                    WHERE p.nombre = ?
                """)) {
                sol = getLocalidadBDS(innombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!inProvincia.isEmpty() && !inProvincia.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM localidad p
                    WHERE p.provincia = ?
                """)) {
                sol = getLocalidadBDS(inProvincia, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }

        return sol;
    }

    private static List<LocalidadBD> getLocalidadBDS(String input, List<LocalidadBD> sol, PreparedStatement statement) throws Exception {
        LocalidadBD item;
        List<LocalidadBD> sol2 = new ArrayList<>();
        statement.setString(1, input);
        ResultSet resultSet = statement.executeQuery();

        if(!resultSet.next()){
            try {
                connection.close();
                mariadbConnect.connclosed();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return new ArrayList<>();} else {
            do {
                String codigo = resultSet.getString("codigo");
                String nombre = resultSet.getString("nombre");
                String provincia = resultSet.getString("provincia");
                item = new LocalidadBD(codigo, nombre, provincia);
                if(sol.contains(item) || sol.isEmpty())
                    sol2.add(item);
            } while (resultSet.next());
            if(sol2.isEmpty()) throw new Exception();
            return sol2;
        }
    }
    //endregion


    //region provincia
    public static List<ProvinciaBD> searchProvincia(String incodigo, String innombre){
        List<ProvinciaBD> sol = new ArrayList<>();
        connection = mariadbConnect.mdbconn();

        if(!incodigo.isEmpty() && !incodigo.isBlank()){
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM provincia p
                    WHERE p.codigo = ?
                """)) {
                sol = getProvinciaBDS(incodigo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } if (!innombre.isEmpty() && !innombre.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM provincia p
                    WHERE p.nombre = ?
                """)) {
                sol = getProvinciaBDS(innombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }
        return sol;
    }

    private static List<ProvinciaBD> getProvinciaBDS(String innombre, List<ProvinciaBD> sol, PreparedStatement statement) throws Exception {
        ProvinciaBD item;
        List<ProvinciaBD> sol2 = new ArrayList<>();
        statement.setString(1, innombre);
        ResultSet resultSet = statement.executeQuery();

        if(!resultSet.next()){
            try {
                connection.close();
                mariadbConnect.connclosed();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return new ArrayList<>();} else {
            do {
                String codigo = resultSet.getString("codigo");
                String nombre = resultSet.getString("nombre");
                item = new ProvinciaBD(codigo, nombre);
                if(sol.contains(item) || sol.isEmpty())
                    sol2.add(item);
            } while (resultSet.next());
            if(sol2.isEmpty()) throw new Exception();
            return sol2;
        }
    }
    //endregion
}
