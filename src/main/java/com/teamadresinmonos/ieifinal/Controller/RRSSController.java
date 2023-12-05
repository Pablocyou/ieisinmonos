package com.teamadresinmonos.ieifinal.Controller;

import com.teamadresinmonos.ieifinal.Extractor.ExtractorCAT;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorCV;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorMUR;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RRSSController {

    @GetMapping(value = "/dunkCAT")
    public static String embedpost(@RequestParam String filename) {
        return "Filas insertadas: " + ExtractorCAT.dunk(filename);
    }
    @GetMapping(value = "/dunkCV")
    public static String embedstep(@RequestParam String filename) {
        try {
            return "Filas insertadas: " + ExtractorCV.dunk(filename);
        }catch(Exception e){return "oof";}
    }
    @GetMapping(value = "/dunkMUR")
    public static String embedprofile(@RequestParam String filename) {
        try {
            return "Filas insertadas: " + ExtractorMUR.dunk(filename);
        }catch(Exception e){return "oof";}
    }

}
