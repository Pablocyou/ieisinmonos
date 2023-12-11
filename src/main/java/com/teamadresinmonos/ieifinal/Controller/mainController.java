package com.teamadresinmonos.ieifinal.Controller;

import com.teamadresinmonos.ieifinal.Extractor.ExtractorCV;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorMUR;
import com.teamadresinmonos.ieifinal.Services.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
