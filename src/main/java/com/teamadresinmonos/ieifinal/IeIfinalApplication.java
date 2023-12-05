package com.teamadresinmonos.ieifinal;

import com.teamadresinmonos.ieifinal.Util.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IeIfinalApplication {
    private static Config config = new Config();
    public static void main(String[] args) {
        SpringApplication.run(IeIfinalApplication.class, args);
        System.out.println("GreenIt API Server " + config.getSrvName());
        if(config.inDebug()){System.out.println("=-= RUNNING IN DEBUG MODE =-=");}else{System.out.println("-=- RUNNING IN AWS MODE -=-");}
        System.out.println("path to resources: " + Config.getResourcesLocation());
        System.out.println("Corriendo sobre: " + System.getProperty("os.name"));
        //test commit
    }

}
