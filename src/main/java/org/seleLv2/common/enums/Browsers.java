package org.seleLv2.common.enums;

import org.seleLv2.utils.LogUtils;

public enum Browsers {
    CHROME,
    FIREFOX,
    EDGE;

    public static Browsers fromString(String browserName) {
        try {
            return Browsers.valueOf(browserName.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            LogUtils.info("Browser '" + browserName + "'NOT SUPPORTED");
            return CHROME;
        }
    }
}