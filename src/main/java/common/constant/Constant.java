package common.constant;

import utils.GetConfigPropertiesUtils;

public class Constant {
    public static String account = GetConfigPropertiesUtils.get("EMAIL");
    public static String password = GetConfigPropertiesUtils.get("PASSWORD");
    public static String url = GetConfigPropertiesUtils.get("URL");
    public static String browser = GetConfigPropertiesUtils.get("BROWSER");
    public static String fullDatetime = "yyMMddhhmmss";
    public static String shortDateUs = "M/d/yyyy";
    public static int timeout = GetConfigPropertiesUtils.getInt("TIMEOUT");
}
