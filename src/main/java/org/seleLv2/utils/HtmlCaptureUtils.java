package org.seleLv2.utils;

import com.codeborne.selenide.WebDriverRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HtmlCaptureUtils {

    private static final String OUTPUT_FOLDER = "reports/html/";

    public static String save(String testName) {
        try {

            if (!WebDriverRunner.hasWebDriverStarted()) {
                LogUtils.info("Driver not started - cannot capture HTML");
                return null;
            }

            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String filePath = OUTPUT_FOLDER + testName + "_" + time + ".html";

            String pageSource = WebDriverRunner.source();
            String currentUrl = WebDriverRunner.url();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Debug info
            writer.write("<!-- ===================== DEBUG INFO ===================== -->\n");
            writer.write("<!-- URL: " + currentUrl + " -->\n");
            writer.write("<!-- TIME: " + time + " -->\n");
            writer.write("<!-- TEST: " + testName + " -->\n");
            writer.write("<!-- ===================================================== -->\n\n");

            writer.write(pageSource);
            writer.close();

            LogUtils.info("HTML saved: " + filePath);

            return filePath;

        } catch (Exception e) {
            LogUtils.info("Failed to save HTML", e);
            return null;
        }
    }
}


