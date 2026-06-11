package org.seleLv2.utils;

import org.seleLv2.elements.Element;

import static org.seleLv2.elements.Element.$;

public class CookieUtils {

    private static final String btn_Accept =
            "//a[@id='cn-accept-cookie']";

    public static void acceptCookiesIfPresent() {

        Element btnAccept = $(btn_Accept);

        if (btnAccept.exists()) {
            btnAccept.click();
        }
    }
}
