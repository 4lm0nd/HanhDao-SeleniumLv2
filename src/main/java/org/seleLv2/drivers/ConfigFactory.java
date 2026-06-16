package org.seleLv2.drivers;

import org.seleLv2.utils.LogUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigFactory {
    private static final Properties properties = ConfigFactory.getProperties();

    public static Properties getProperties() {

        String path = "config/config.properties";
        Properties p = new Properties();
        LogUtils.info("--- DEBUG CONFIG ---");
        URL root = Thread.currentThread().getContextClassLoader().getResource(".");
        LogUtils.info("Root Classpath: " + (root != null ? root.getPath() : "Not found"));

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                LogUtils.info("!!! Not Found " + path);
            } else {
                p.load(is);
                LogUtils.info("--- Load file successfully! ---");
            }
        } catch (Exception e) {
            LogUtils.info(e.getStackTrace());
        }
        return p;
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
