package org.seleLv2.elements;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;


public class Tables extends Elements {

    private final String xpath;

    public Tables(String xpath) {
        super(xpath);
        this.xpath = xpath;
    }


    public static Tables $(String xpath) {
        return new Tables(xpath);
    }

    // ===== GET COLUMN DATA =====
    public List<String> getColumnData(int columnIndex) {
        return $$x(xpath + "//tr").stream()
                .map(row -> row.$$x(".//td"))
                .filter(cells -> columnIndex < cells.size())
                .map(cells -> cells.get(columnIndex).getText())
                .collect(Collectors.toList());
    }

    public String getCellValueByHeader(String columnName) {

        List<String> headerTexts = $$x(xpath + "//th").texts();
        int index = headerTexts.indexOf(columnName);
        if (index == -1) return "Column not found";
        return $$x(xpath + "//tbody/tr").first()
                .$$x("./td").get(index)
                .getText();
    }
}