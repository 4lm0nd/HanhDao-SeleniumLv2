package org.seleLv2.common.enums;

public enum AccountMenuItems {
    ORDER("my-account/orders/");

    private final String items;

    AccountMenuItems (String items){
        this.items = items;
    }
    public String getItems()
    {
        return items;
    }
}
