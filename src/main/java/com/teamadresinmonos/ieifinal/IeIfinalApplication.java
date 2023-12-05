package com.teamadresinmonos.ieifinal;

import com.teamadresinmonos.ieifinal.Util.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IeIfinalApplication {
    private static Config config = new Config();
    public static void main(String[] args) {
        SpringApplication.run(IeIfinalApplication.class, args);
        if(config.inDebug()){System.out.println("=-= RUNNING IN DEBUG MODE =-=");}else{System.out.println("-=- RUNNING IN IEI MODE -=-");}
        System.out.println("path to resources: " + Config.getResourcesLocation());
        System.out.println("Corriendo sobre: " + System.getProperty("os.name"));
        System.out.println("Recuerda poner los archivos en C:\\ (el db.properties) y en C:\\Data\\ los datos");
        System.out.println("Los nombres de datos son CAT.xml, CV.csv, MUR.json para las versiones cortas");
        System.out.println("y zlongCAT.xml, zlongCV.csv, zlongMUR.json para las versiones largas");
        System.out.println("puedes poner nuevos datos y probarlos diciendo el nombre en la peticion web");
        System.out.println("http://localhost:8080/dunkMUR?filename=MUR.json");
    }

}
