package com.teamadresinmonos.ieifinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class RRSSController {

    @GetMapping(value = "/rrsspost")
    public static String embedpost(@RequestParam int postid) {
        return "";
    }
    @GetMapping(value = "/rrssstep")
    public static String embedstep(@RequestParam int stepid) {
        return "";
    }
    @GetMapping(value = "/rrssprofile")
    public static String embedprofile(@RequestParam String username) {
        return "";
    }

}
