package org.seleLv2.utils;

import org.seleLv2.common.constant.Constant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.seleLv2.drivers.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static void takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        String timestamp = new SimpleDateFormat(Constant.fullDatetime).format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LogUtils.info("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            LogUtils.info(e.getMessage());
        }
    }
}
