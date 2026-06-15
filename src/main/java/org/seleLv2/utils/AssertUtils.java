package org.seleLv2.utils;

import org.apache.hc.core5.function.Supplier;
import org.seleLv2.common.constant.Constant;
import org.testng.Assert;

import static org.seleLv2.utils.DataUtils.normalizeTestSpacing;
import static org.seleLv2.utils.WaitUtils.sleep;


public class AssertUtils {

    public static void assertEquals(Supplier<String> actualSupplier,  String expected) {

            String actual = actualSupplier.get();
            Assert.assertEquals(
                    actual,
                    expected,
                    String.format(
                            "Expected [%s] but got [%s]",
                            expected,actual
                    ));
    }

    public static void assertContains(Supplier<String> actualSupplier, String expected) {
            String actual = actualSupplier.get();
            Assert.assertTrue(
                    normalizeTestSpacing(actual)
                            .toLowerCase()
                            .contains(
                                    normalizeTestSpacing(expected)
                                            .toLowerCase()
                            )
            );

    }


    public static void retryAssert(
            Runnable assertion,
            int timeoutSeconds,
            int pollingMillis) {

        long endTime = System.currentTimeMillis()
                + timeoutSeconds * 1000L;

        AssertionError lastError = null;

        while (System.currentTimeMillis() < endTime) {

            try {

                assertion.run();
                return;

            } catch (AssertionError e) {

                lastError = e;
                sleep(pollingMillis);
            }
        }

        throw lastError;
    }


    public static void assertActiveMode(boolean activeMode, int timeOut) {
        retryAssert(() -> Assert.assertTrue(activeMode),
                timeOut,
                Constant.timeInMilliSecond);

    }
}


