package org.seleLv2.common.constant;


import org.seleLv2.drivers.ConfigFactory;
import org.seleLv2.utils.JsonUtils;

public class Constant {

    //Login Data
    public static String account = JsonUtils.getJsonValue("login.json","username");
    public static String password = JsonUtils.getJsonValue("login.json","password");

    //Billing Data
    public static String firstname = JsonUtils.getJsonValue("billing.json","firstname");
    public static String lastname = JsonUtils.getJsonValue("billing.json","lastname");
    public static String street = JsonUtils.getJsonValue("billing.json","street");
    public static String town = JsonUtils.getJsonValue("billing.json","town");
    public static String zipcode = JsonUtils.getJsonValue("billing.json","zipcode");
    public static String phone = JsonUtils.getJsonValue("billing.json","phone");
    public static String email = JsonUtils.getJsonValue("billing.json","email");

    //Config Data
    public static String fullDatetime = "yyMMddhhmmss";
    public static String shortDateUs = "M/d/yyyy";
    public static int timeout = ConfigFactory.getInt("timeOut");
    public static int shortTime = ConfigFactory.getInt("shortTime");
    public static String reportPath = ConfigFactory.get("reportPath");
}
