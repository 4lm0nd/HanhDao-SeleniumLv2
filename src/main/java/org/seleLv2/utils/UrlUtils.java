package org.seleLv2.utils;

import org.seleLv2.drivers.ConfigFactory;

public class UrlUtils {

    public static String getBaseUrl() {
        return ConfigFactory.get("url");
    }

    public static String getUrl(String... paths) {
        return getBaseUrl() + "/" + String.join("/", paths);
    }

    public static String getQueryParam( String param, String key){
        return param.contains("?")
                ? param.substring(param.indexOf(key))
                : "";
    }
}

