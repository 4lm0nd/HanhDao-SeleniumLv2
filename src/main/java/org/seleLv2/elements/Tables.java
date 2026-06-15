package org.seleLv2.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleLv2.drivers.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class Tables extends Element {

    private final String xpath;

    public Tables(String xpath) {
        super(xpath);
        this.xpath = xpath;
    }

    public static List<String> getColumnValuesByHeader(String headerName) {

        WebDriver driver = DriverManager.getDriver();

        List<WebElement> headers =
                driver.findElements(By.cssSelector("table thead th"));

        int columnIndex = -1;

        for (int i = 0; i < headers.size(); i++) {

            String headerText = headers.get(i)
                    .getText()
                    .trim();

            if (headerText.equalsIgnoreCase(headerName)) {
                columnIndex = i + 1; // XPath bắt đầu từ 1
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException(
                    "Header not found: " + headerName);
        }

        List<WebElement> cells =
                driver.findElements(
                        By.xpath("//table/tbody/tr/td[" + columnIndex + "]"));

        List<String> values = new ArrayList<>();

        for (WebElement cell : cells) {
            values.add(cell.getText().trim());
        }

        return values;
    }
}