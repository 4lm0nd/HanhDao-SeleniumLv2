package org.seleLv2.drivers;

import java.util.Properties;

public class ConfigManager {
    private static  Properties props ;

    static {
        try {
            props = ConfigFactory.getProperties();
        } catch (Exception e) {
            props = new Properties();
            System.err.println("CANNOT LOAD CONFIG FILE");
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if (value != null) return value;

        value = System.getenv(key);
        if (value != null) return value;
        value = props.getProperty(key);

        if (value == null) {

            throw new RuntimeException("Key '" + key + "'NOT FOUND (File, System, Env)!");
        }

        return value;
    }

}
