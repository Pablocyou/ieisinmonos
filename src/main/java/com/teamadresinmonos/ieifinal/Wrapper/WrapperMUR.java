package com.teamadresinmonos.ieifinal.Wrapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.teamadresinmonos.ieifinal.Util.Config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WrapperMUR {

    public static List<String> kebab(String filename) throws Exception {
        System.out.println("Wrapper MUR: " + Config.getDataLocation() + filename);
        // Paso .json a String
        String fileContent = new String(Files.readAllBytes(Paths.get(Config.getDataLocation() + filename)));

        // Parse the string into a JsonArray
        JsonArray jsonArray = JsonParser.parseString(fileContent).getAsJsonArray();

        // List to hold JSON objects as strings
        List<String> jsonStrings = new ArrayList<>();

        // Iterate through the JsonArray and convert each element to a string
        for (JsonElement element : jsonArray) {
            jsonStrings.add(element.toString());
        }

        return jsonStrings;
    }

}
