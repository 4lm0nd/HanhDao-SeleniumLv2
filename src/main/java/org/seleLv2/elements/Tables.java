package org.seleLv2.elements;

import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class Tables extends Elements {

    private final String xpath;

    public Tables(String xpath) {
        super(xpath);
        this.xpath = xpath;
    }

    public static Tables $(String xpath) {
        return new Tables(xpath);
    }

    public static List<String> getColumnValuesByHeader(String headerName) {
        List<SelenideElement> headers = $$("table thead th");

        int columnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(headerName)) {
                columnIndex = i + 1; // XPath index bắt đầu từ 1
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException("Header not found: " + headerName);
        }

     return $$x("//table/tbody/tr/td[" + columnIndex + "]")
                .texts();
    }
}
