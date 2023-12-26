package com.teamadresinmonos.ieifinal.Controller;

import com.teamadresinmonos.ieifinal.Entities.BaseDatos.CentroBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.LocalidadBD;
import com.teamadresinmonos.ieifinal.Entities.BaseDatos.ProvinciaBD;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorCV;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorMUR;
import com.teamadresinmonos.ieifinal.Services.DataManagerService;
import com.teamadresinmonos.ieifinal.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class mainController {

    private final DataManagerService dataManagerService;

    @Autowired
    public mainController(DataManagerService dataManagementService){
        this.dataManagerService = dataManagementService;
    }
    @GetMapping(value = "/dunkCAT")
    public String dunkCat(@RequestParam String filename) {
        return "Filas insertadas: " + dataManagerService.dunkCAT(filename);
    }
    @GetMapping(value = "/dunkCV")
    public String dunkCV(@RequestParam String filename) {
        try {
            return "Filas insertadas: " + dataManagerService.dunkCV(filename);
        }catch(Exception e){return "oof";}
    }
    @GetMapping(value = "/dunkMUR")
    public String dunkMUR(@RequestParam String filename) {
        try {
            return "Filas insertadas: " + dataManagerService.dunkMUR(filename);
        }catch(Exception e){return "oof";}
    }

    @GetMapping(value = "/dunkMUR_CV")
    public String dunkMUR_CV(@RequestParam String filenameMUR, @RequestParam String filenameCV) {
        try {
            int a = dataManagerService.dunkCV(filenameCV);
            return "Filas insertadas: " + (dataManagerService.dunkMUR(filenameMUR) + a);
        }catch(Exception e){return "oof";}
    }

    @GetMapping(value = "/dunkMUR_CAT")
    public String dunkMUR_CAT(@RequestParam String filenameMUR, @RequestParam String filenameCAT) {
        try {
            int a = dataManagerService.dunkCAT(filenameCAT);
            return "Filas insertadas: " + (dataManagerService.dunkMUR(filenameMUR) + a);
        }catch(Exception e){return "oof";}
    }

    @GetMapping(value = "/dunkCV_CAT")
    public String dunkCV_CAT(@RequestParam String filenameCV, @RequestParam String filenameCAT) {
        try {
            int a = dataManagerService.dunkCV(filenameCV);
            return "Filas insertadas: " + (dataManagerService.dunkCAT(filenameCAT) + a);
        }catch(Exception e){return "oof";}
    }

    @GetMapping(value = "/dunkALL")
    public String dunkALL(@RequestParam String filenameMUR, @RequestParam String filenameCV, @RequestParam String filenameCAT) {
        try {
            int a = dataManagerService.dunkCV(filenameCV);
            int b = dataManagerService.dunkCAT(filenameCAT);
            return "Filas insertadas: " + (dataManagerService.dunkMUR(filenameMUR) + a + b);
        }catch(Exception e){return "oof";}
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
