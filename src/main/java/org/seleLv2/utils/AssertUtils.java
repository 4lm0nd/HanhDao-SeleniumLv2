package org.seleLv2.utils;

import org.apache.hc.core5.function.Supplier;
import org.seleLv2.common.constant.Constant;
import org.testng.Assert;



public class AssertUtils {

    public static void assertEquals(
            Supplier<String> actualSupplier,
            String expected) {

        retryAssert(() -> {

            String actual = actualSupplier.get();

            Assert.assertEquals(
                    actual,
                    expected,
                    String.format(
                            "Expected [%s] but got [%s]",
                            expected,
                            actual)
            );

        }, Constant.timeInSecond, Constant.timeInMilliSecond);
    }

    public static void assertContains(
            Supplier<String> actualSupplier,
            String expected) {

        retryAssert(() -> {

            String actual = actualSupplier.get();

            Assert.assertTrue(
                    normalize(actual)
                            .toLowerCase()
                            .contains(
                                    normalize(expected)
                                            .toLowerCase()
                            )
            );

        }, Constant.timeInSecond, 100);
    }

    private static String normalize(String input) {
        return input
                .replace("\u00A0", " ")
                .replaceAll("\\s+", " ")
                .trim();
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

    private static void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public static void assertActiveMode(boolean activeMode) {
        retryAssert(() -> Assert.assertTrue(activeMode),
                Constant.timeInSecond,
                Constant.timeInMilliSecond);

    }
}


