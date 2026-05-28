package org.seleLv2.utils;

import org.seleLv2.common.constant.Constant;
import org.testng.Assert;

public class AssertUtils {

    public static void assertEquals(String actual, String expected, int timeout) {

      retryAssert(() ->

        Assert.assertEquals(
                actual,
                expected,
                String.format("Expected [%s] but got [%s]", expected, actual)
        ),
              timeout,
              100
      );
    }

    public static void assertContains(String actual, String expected, int timeout) {
        String a = normalize(actual);
        String e = normalize(expected);

       retryAssert(() ->

        Assert.assertTrue(
                a.toLowerCase().contains(e.toLowerCase()),
                String.format("Actual [%s] to contain [%s]", actual, expected)
        ),
               timeout,
               100
        );
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
}


