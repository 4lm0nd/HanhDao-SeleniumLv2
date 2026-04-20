package org.seleLv2.utils;

import com.codeborne.selenide.Selenide;
import org.seleLv2.common.constant.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String takeScreenshot(String testName) {

        String timestamp = new SimpleDateFormat(Constant.fullDatetime)
                .format(new Date());

        String fileName = testName + "_" + timestamp;

        String path = Selenide.screenshot(fileName);

        LogUtils.info("Screenshot saved: " + path);

        return path;
    }
}
