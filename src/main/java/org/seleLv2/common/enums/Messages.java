package org.seleLv2.common.enums;

public enum Messages {
    MSG_EMPTY_CARD("YOUR SHOPPING CART IS EMPTY"),
    MSG_ORDER_CONFIRMATION("THANK YOU. YOUR ORDER HAS BEEN RECEIVED.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


