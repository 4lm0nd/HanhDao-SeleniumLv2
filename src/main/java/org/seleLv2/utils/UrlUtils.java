package org.seleLv2.utils;

public class UrlUtils {

    public static String getBaseUrl() {
        return GetConfigPropertiesUtils.get("URL");
    }
    public static String getUrl(String... paths) {
        return getBaseUrl() + "/" + String.join("/", paths);
    }

}
