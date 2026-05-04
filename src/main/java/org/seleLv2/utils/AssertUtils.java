package org.seleLv2.utils;

import org.testng.Assert;

public class AssertUtils {

    public static void assertEquals(String actual, String expected) {
        Assert.assertEquals(
                actual,
                expected,
                String.format("Expected [%s] but got [%s]", expected, actual)
        );
    }

    public static void assertContains(String actual, String expected) {
        String a = normalize(actual);
        String e = normalize(expected);
        Assert.assertTrue(
                a.toLowerCase().contains(e.toLowerCase()),
                String.format("Actual [%s] to contain [%s]", actual, expected)
        );
    }

    private static String normalize(String input) {
        return input
                .replace("\u00A0", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}


