package org.seleLv2.common.enums;

public enum Messages {
    MSG_EMPTY_CARD("YOUR SHOPPING CART IS EMPTY"),
    MSG_ORDER_CONFIRMATION("THANK YOU. YOUR ORDER HAS BEEN RECEIVED."),
    MSG_ERROR_FIRSTNAME("Billing First name is a required field."),
    MSG_ERROR_LASTNAME("Billing Last name is a required field."),
    MSG_ERROR_STREET("Billing Street address is a required field."),
    MSG_ERROR_TOWN("Billing Town / City is a required field."),
    MSG_ERROR_ZIP("Billing ZIP Code is a required field."),
    MSG_ERROR_PHONE("Billing Phone is a required field."),
    MSG_ERROR_EMAIL("Billing Email address is a required field.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


