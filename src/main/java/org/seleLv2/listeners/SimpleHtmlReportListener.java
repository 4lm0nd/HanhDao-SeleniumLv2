package org.seleLv2.listeners;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleHtmlReportListener implements ITestListener {
    private BufferedWriter writer;

    @Override
    public void onStart(ITestContext context) {
        try {
            writer = new BufferedWriter(new FileWriter(Constant.reportPath));
            writer.write("<html><head><title>Test Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial; margin: 20px; }");
            writer.write("table { border-collapse: collapse; width: 100%; }");
            writer.write("th, td { padding: 8px 12px; border: 1px solid #ccc; vertical-align: top; }");
            writer.write("th { background-color: #f2f2f2; }");
            writer.write(".pass { color: green; font-weight: bold; }");
            writer.write(".fail { color: red; font-weight: bold; }");
            writer.write(".skip { color: orange; font-weight: bold; }");
            writer.write("td.details { white-space: pre-line; }");
            writer.write("</style></head><body>");
            writer.write("<h2>Test Execution Report</h2>");
            writer.write("<table>");
            writer.write("<tr><th>Test Name</th><th>Status</th><th>Start Time</th><th>End Time</th><th>Duration (ms)</th><th>Details</th></tr>");
        } catch (IOException e) {
            LogUtils.info(e.getMessage());
        }
    }

    private void writeResult(ITestResult result, String statusClass, String statusText) {
        try {
            String testName = result.getMethod().getMethodName();
            String startTime = formatDate(result.getStartMillis());
            String endTime = formatDate(result.getEndMillis());
            long duration = result.getEndMillis() - result.getStartMillis();

            String details = "";
            if (result.getThrowable() != null) {
                details = result.getThrowable().getMessage() + "\n";
                for (StackTraceElement element : result.getThrowable().getStackTrace()) {
                    details += "    at " + element.toString() + "\n";
                }
            } else {
                details = "Test passed successfully.";
            }
            writer.write("<tr>");
            writer.write("<td>" + testName + "</td>");
            writer.write("<td class='" + statusClass + "'>" + statusText + "</td>");
            writer.write("<td>" + startTime + "</td>");
            writer.write("<td>" + endTime + "</td>");
            writer.write("<td>" + duration + "</td>");
            writer.write("<td class='details'>" + details + "</td>");
            writer.write("</tr>");
        } catch (IOException e) {
            LogUtils.info(e.getMessage());
        }
    }

    private String formatDate(long millis) {
        return new SimpleDateFormat("MM.dd.yyyy hh:mm:ss a").format(new Date(millis));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writeResult(result, "pass", "Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writeResult(result, "fail", "Fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeResult(result, "fail", "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            writer.write("</table></body></html>");
            writer.close();
            LogUtils.info("Report generated at: " + Constant.reportPath);
        } catch (IOException e) {
            LogUtils.info(e.getMessage());
        }
    }
}
