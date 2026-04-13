package org.seleLv2.utils;

import com.google.gson.stream.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.io.InputStream;


public class JsonUtils {
    public static String getJsonValue(String fileName, String key) {
        try {
            // Load file from resources folder
            ClassLoader classLoader = JsonReader.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);

            if (inputStream == null) {
                System.err.println("File Not Found: " + fileName);
                return null;
            }

            // Read and parse JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream, "UTF-8"));

            return (String) jsonObject.get(key);

        } catch (Exception e) {
            System.err.println("Error Reading JSON: " + e.getMessage());
            return null;
        }
    }
    }


