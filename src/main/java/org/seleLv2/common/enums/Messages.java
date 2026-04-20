package org.seleLv2.common.enums;

public enum Messages {
    MSG_EMPTY_CARD("YOUR SHOPPING CART IS EMPTY"),
    MSG_PLACE_ORDER("Thank you. Your order has been received");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


