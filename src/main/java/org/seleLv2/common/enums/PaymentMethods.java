package org.seleLv2.common.enums;

public enum PaymentMethods {
    CHECK_ON_PAYMENT("bacs","Direct bank transfer"),
    DIRECT_BANK_TRANSFER("cheque","Check payments"),
    CASH_ON_DELIVERY("cod","Cash on delivery");

    private final String method;
    private final String methodName;

    PaymentMethods (String method, String methodName) {
        this.method = method;
        this.methodName = methodName;
    }

    public String getPaymentMethod() {
        return method;
    }

    public String getMethodName(){
        return methodName;
    }
}
