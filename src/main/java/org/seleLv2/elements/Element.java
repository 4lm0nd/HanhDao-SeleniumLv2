package org.seleLv2.elements;

public class Element extends BaseElement {

    public Element(String xpath) {
        super(xpath);
    }

    public static Element $(String xpath) {
        return new Element(xpath);
    }
}






