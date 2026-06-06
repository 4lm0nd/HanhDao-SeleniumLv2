package org.seleLv2.utils;

import org.openqa.selenium.WebDriver;
import org.seleLv2.drivers.DriverManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HtmlCaptureUtils {

    private static final String OUTPUT_FOLDER = "reports/html/";

    public static String save(String testName) {

            try {

                WebDriver driver = DriverManager.getDriver();

                if (driver == null) {
                    LogUtils.info("Driver is null");
                    return null;
                }

                testName = testName.replaceAll("[\\\\/:*?\"<>|]", "_");

                File folder = new File(OUTPUT_FOLDER);
                folder.mkdirs();

                String time = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                String filePath =
                        OUTPUT_FOLDER + testName + "_" + time + ".html";

                try (BufferedWriter writer =
                             new BufferedWriter(new FileWriter(filePath))) {

                    writer.write("<!-- URL: " + driver.getCurrentUrl() + " -->\n");
                    writer.write(driver.getPageSource());
                }

                LogUtils.info("HTML saved: " + filePath);
                return filePath;

            } catch (Exception e) {
                LogUtils.info("Failed to save HTML", e);
                e.printStackTrace();
                return null;
            }
        }
    }



