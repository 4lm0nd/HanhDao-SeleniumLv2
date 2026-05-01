package org.seleLv2.dataprovider;

import org.seleLv2.common.enums.PaymentMethods;
import org.testng.annotations.DataProvider;

public class PaymentData {

    @DataProvider(name = "paymentMethods")
    public Object[][] paymentMethod() {
        return new Object[][]{
                {
                        PaymentMethods.CASH_ON_DELIVERY.getPaymentMethod(),
                        PaymentMethods.CASH_ON_DELIVERY.getMethodName()

                },
                {
                        PaymentMethods.CHECK_ON_PAYMENT.getPaymentMethod(),
                        PaymentMethods.CHECK_ON_PAYMENT.getMethodName()
                },
        };

    }
}


