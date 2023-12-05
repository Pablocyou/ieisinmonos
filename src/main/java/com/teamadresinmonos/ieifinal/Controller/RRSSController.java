package com.teamadresinmonos.ieifinal.Controller;

import com.teamadresinmonos.ieifinal.Extractor.ExtractorCAT;
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
        return "Filas insertadas: ";
    }
    @GetMapping(value = "/dunkMUR")
    public static String embedprofile(@RequestParam String filename) {
        return "Filas insertadas: ";
    }

}
