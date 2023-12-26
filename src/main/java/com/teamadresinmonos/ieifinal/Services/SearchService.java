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
                                              double longitud, double latitud, String telefono, String descripcion, String localidad){
        List<CentroBD> sol = new ArrayList<>();
        connection = mariadbConnect.mdbconn();

        if(!nombre.isEmpty() && !nombre.isBlank()){
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.nombre = ?
                """)) {
                return getCentroBDS(nombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!tipo.isEmpty() && !tipo.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.tipo = ?
                """)) {
                return getCentroBDS(tipo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!direccion.isEmpty() && !direccion.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.provincia = ?
                """)) {
                return getCentroBDS(direccion, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (codigoPostal != 0) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.codigo_postal = ?
                """)) {
                return getCentroBDS(codigoPostal + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (longitud != 69.420) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.longitud = ?
                """)) {
                return getCentroBDS(longitud + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (latitud != 69.420) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.latitud = ?
                """)) {
                return getCentroBDS(latitud + "", sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!telefono.isEmpty() && !telefono.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.telefono = ?
                """)) {
                return getCentroBDS(telefono, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!descripcion.isEmpty() && !descripcion.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.descripcion = ?
                """)) {
                return getCentroBDS(descripcion, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }else if (!localidad.isEmpty() && !localidad.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM centro p
                    WHERE p.localidad = ?
                """)) {
                return getCentroBDS(localidad, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    private static List<CentroBD> getCentroBDS(String input, List<CentroBD> sol, PreparedStatement statement) throws SQLException {
        CentroBD item;
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
                int cp = resultSet.getInt("codigo_postal");
                double longitud = resultSet.getDouble("longitud");
                double latitud = resultSet.getDouble("latitud");
                String telefono = resultSet.getString("telefono");
                String descripcion = resultSet.getString("descripcion");
                String localidad = resultSet.getString("localidad");
                item = new CentroBD(nombre, tipo, direccion, cp, longitud, latitud, telefono, descripcion, localidad);
                sol.add(item);
            } while (resultSet.next());
            return sol;
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
                return getLocalidadBDS(incodigo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!innombre.isEmpty() && !innombre.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM localidad p
                    WHERE p.nombre = ?
                """)) {
                return getLocalidadBDS(innombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!inProvincia.isEmpty() && !inProvincia.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM localidad p
                    WHERE p.provincia = ?
                """)) {
                return getLocalidadBDS(inProvincia, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    private static List<LocalidadBD> getLocalidadBDS(String input, List<LocalidadBD> sol, PreparedStatement statement) throws SQLException {
        LocalidadBD item;
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
                sol.add(item);
            } while (resultSet.next());
            return sol;
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
                return getProvinciaBDS(incodigo, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        } else if (!innombre.isEmpty() && !innombre.isBlank()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM provincia p
                    WHERE p.nombre = ?
                """)) {
                return getProvinciaBDS(innombre, sol, statement);
            } catch (Exception e) {
                System.out.println("Error al recuperar info de la BD");
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    private static List<ProvinciaBD> getProvinciaBDS(String innombre, List<ProvinciaBD> sol, PreparedStatement statement) throws SQLException {
        ProvinciaBD item;
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
                sol.add(item);
            } while (resultSet.next());
            return sol;
        }
    }
    //endregion
}
