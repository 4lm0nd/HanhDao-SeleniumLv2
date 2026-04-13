package org.seleLv2.common.constant;

import org.seleLv2.utils.GetConfigPropertiesUtils;
import org.seleLv2.utils.JsonUtils;

public class Constant {
    public static String account = JsonUtils.getJsonValue("login.json","username");
    public static String password = JsonUtils.getJsonValue("login.json","password");
    public static String url = GetConfigPropertiesUtils.get("URL");
    public static String browser = GetConfigPropertiesUtils.get("BROWSER");
    public static String fullDatetime = "yyMMddhhmmss";
    public static String shortDateUs = "M/d/yyyy";
    public static int timeout = GetConfigPropertiesUtils.getInt("TIMEOUT");
    public static String reportPath = "reports/SimpleReport.html";
}
