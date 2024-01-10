package com.teamadresinmonos.ieifinal.Controller;

import com.teamadresinmonos.ieifinal.Entities.BaseDatos.CentroBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.LocalidadBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.ProvinciaBD;
import com.teamadresinmonos.ieifinal.Services.DataManagerService;
import com.teamadresinmonos.ieifinal.Services.SearchService;
import com.teamadresinmonos.ieifinal.Util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class mainController {

    private final DataManagerService dataManagerService;

    @Autowired
    public mainController(DataManagerService dataManagementService){
        this.dataManagerService = dataManagementService;
    }

    //Método para insertar en la base de datos
    @RequestMapping(value = "/dunk", method = RequestMethod.POST, consumes = "application/json")
    public String dunk(@RequestBody Map<String,String> filename) {
        try {
            String msgCV = "";
            System.out.println(filename);
            System.out.println("CV: " + filename.get("filenameCV"));
            System.out.println("CAT: " + filename.get("filenameCAT"));
            System.out.println("MUR: " + filename.get("filenameMUR"));
            String a = "";
            String b = "";
            String c = "";
            File f = new File(Config.getDataLocation() + filename.get("filenameCV"));
            if(f.exists() && !f.isDirectory()) {
                a = dataManagerService.dunkCV(filename.get("filenameCV"));
            }

            //if(a <0){msgCV = "Fallo de conexión con www.coordenadas-gps.com"; a = -a;}


            f = new File(Config.getDataLocation() + filename.get("filenameCAT"));
            if(f.exists() && !f.isDirectory()) {
                b = dataManagerService.dunkCAT(filename.get("filenameCAT"));
            }

            f = new File(Config.getDataLocation() + filename.get("filenameMUR"));
            if(f.exists() && !f.isDirectory()) {
                c = dataManagerService.dunkMUR(filename.get("filenameMUR"));
            }

            return "" + a + b + c;
            //return "Filas insertadas: " + (c + a + b) + " " + msgCV;
        }catch(Exception e){
            e.printStackTrace();
            return "oof";}
    }

    @GetMapping(value = "/lookupCentro")
    public List<CentroBD> searchCentro(@RequestParam String nombre, @RequestParam String tipo, @RequestParam String direccion,
                                 @RequestParam int codigoPostal, @RequestParam double longitud, @RequestParam double latitud,
                                 @RequestParam String telefono, @RequestParam String descripcion, @RequestParam String localidad, @RequestParam String comunidad){
        return SearchService.searchCentro(nombre, tipo, direccion, codigoPostal, longitud, latitud, telefono, descripcion, localidad, comunidad);
    }
    @GetMapping(value = "/lookupLocalidad")
    public List<LocalidadBD> searchLocalidad(@RequestParam String codigo, @RequestParam String nombre, @RequestParam String provincia){
        return SearchService.searchLocalidad(codigo, nombre, provincia);
    }
    @GetMapping(value = "/lookupProvincia")
    public List<ProvinciaBD> searchProvincia(@RequestParam String codigo, @RequestParam String nombre){
        return SearchService.searchProvincia(codigo, nombre);
    }
}
