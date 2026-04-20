package org.seleLv2.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class ElementList {

    private final String xpath;
    private final ElementsCollection elements;

    // ===== CONSTRUCTOR =====
    public ElementList(String xpath) {
        this.xpath = xpath;
        this.elements = $$x(xpath);
    }

    // ===== FACTORY =====
    public static ElementList $$(String xpath) {
        return new ElementList(xpath);
    }

    // ===== GET ALL =====
    public ElementsCollection gets() {
        return elements;
    }

    // ===== GET BY INDEX =====
    public SelenideElement get(int index) {
        return elements.get(index);
    }

    // ===== SIZE =====
    public int size() {
        return elements.size();
    }

    // ===== RANDOM =====
    public SelenideElement getRandom() {
        int index = (int) (Math.random() * elements.size());
        return elements.get(index);
    }

    // ===== FIRST =====
    public SelenideElement first() {
        return elements.first();
    }

    // ===== LAST =====
    public SelenideElement last() {
        return elements.last();
    }
}