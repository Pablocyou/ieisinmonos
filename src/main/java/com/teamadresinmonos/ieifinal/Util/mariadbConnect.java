package com.teamadresinmonos.ieifinal.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

public class mariadbConnect {
    /**
     * Esta clase se encarga de la conexción con la BD de MariaDB (o cualquier BD SQL)
     * Copiada del proyecto de PIN, por eso lo del buffering de conexiones y algunas cosas de logging
     * como el picocons (Pico de conexciones) o cons (conexiones)
     * No es relevante para IEI
     * */
    private static Config config = new Config();

    private static int picocons = 0;
    private static int cons = 0;
    public static Connection mdbconn() {
        Connection connection = null;
        try {
            //buffering de conexiones, si tardas en crear la conexión cada hilo de forma aleatoria es menos
            //probable que abras 30 conexiones simultáneas, porque das tiempo entre espera y espera a que acaben
            //otros hilos
            //prob podria mejorarse con un int compartido de nº de conexiones abiertas pero meh
            Thread.sleep(new Random().nextInt(1,3)*200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(
                    config.getMdbURL(), config.getMdbUser(), config.getMdbPass()
            );
            cons++;
            if(cons>picocons)picocons=cons;
        } catch (Exception e) {
            System.out.println("Error al conectar con la BD de mariaDB " + e);
        }
        return connection;
    }
    public static void connclosed(){cons--;}
    public static int getPicocons(){return picocons;}
}
