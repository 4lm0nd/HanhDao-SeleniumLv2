package org.seleLv2.utils;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    public static <T> T getJsonValue(String fileName, String jsonPath) {

        try (InputStream inputStream =
                     JsonUtils.class.getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "File not found: " + fileName);
            }

            String json =
                    new String(inputStream.readAllBytes(),
                            StandardCharsets.UTF_8);

            return JsonPath.read(json, jsonPath);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error reading json file: " + fileName,
                    e);
        }
    }
}
