package org.seleLv2.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random rand = new Random();

    public static int getRandomIndex(int size) {
        return rand.nextInt(size);
    }
}