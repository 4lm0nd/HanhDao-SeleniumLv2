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
        // 1. Ưu tiên System Property (-D)
        String value = System.getProperty(key);
        if (value != null) return value;

        // 2. Ưu tiên Env Var
        value = System.getenv(key);
        if (value != null) return value;

        // 3. Lấy từ file (nếu props không null và có key)
        value = props.getProperty(key);

        if (value == null) {

            throw new RuntimeException("Key '" + key + "'NOT FOUND (File, System, Env)!");
        }

        return value;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static long getLong(String key) {
        return Long.parseLong(get(key));
    }
}
