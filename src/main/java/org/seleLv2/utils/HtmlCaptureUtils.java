package org.seleLv2.utils;

import org.openqa.selenium.WebDriver;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.drivers.DriverManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class HtmlCaptureUtils {

        private static final String OUTPUT_FOLDER = Constant.reportPath;

        public static String save(String testName) {
            try {
                WebDriver driver = DriverManager.getDriver();

                if (driver == null) {
                    System.out.println("Driver is null - cannot capture HTML");
                    return null;
                }

                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.fullDatetime));
                String filePath = OUTPUT_FOLDER + testName + "_" + time + ".html";
                String pageSource = driver.getPageSource();
                String currentUrl = driver.getCurrentUrl();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                // thêm info debug vào đầu file
                writer.write("<!-- ===================== DEBUG INFO ===================== -->\n");
                writer.write("<!-- URL: " + currentUrl + " -->\n");
                writer.write("<!-- TIME: " + time + " -->\n");
                writer.write("<!-- TEST: " + testName + " -->\n");
                writer.write("<!-- ===================================================== -->\n\n");
                writer.write(pageSource);
                writer.close();
                System.out.println("HTML saved: " + filePath);
                return filePath;

            } catch (Exception e) {
              LogUtils.info("Failed to save HTML");
                e.printStackTrace();
                return null;
            }
        }
    }


