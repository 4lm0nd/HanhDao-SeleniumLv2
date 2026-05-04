package org.seleLv2.common.enums;

public enum HeaderItems {
    MY_ACCOUNT("my-account/"),
    SHOPPING_CARD("cart/"),
    TAB_SHOP("shop/");

    private final String items;

    HeaderItems  (String items) {
        this.items = items;
     }

    public String getItems()
        {
        return items;
    }
}
