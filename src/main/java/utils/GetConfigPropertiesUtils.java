package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

    public class GetConfigPropertiesUtils {

        private static Properties properties = new Properties();

        static {
            try {
                InputStream input = GetConfigPropertiesUtils.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties");

                if (input == null) {
                    throw new RuntimeException("Cannot find config.properties");
                }

                properties.load(input);

            } catch (Exception e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }

        // ===== Get value =====
        public static String get(String key) {
            return properties.getProperty(key);
        }

        // ===== Get with default =====
        public static String get(String key, String defaultValue) {
            return properties.getProperty(key, defaultValue);
        }

        // ===== Get int =====
        public static int getInt(String key) {
            return Integer.parseInt(properties.getProperty(key));
        }

        // ===== Get boolean =====
        public static boolean getBoolean(String key) {
            return Boolean.parseBoolean(properties.getProperty(key));
        }
    }

