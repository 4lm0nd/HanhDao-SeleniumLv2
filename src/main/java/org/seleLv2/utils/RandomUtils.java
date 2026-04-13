package org.seleLv2.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random rand = new Random();

    public static int getRandomIndex(int size) {
        return rand.nextInt(size);
    }

    public static <T> T getRandomItem(List<T> list) {
        return list.get(getRandomIndex(list.size()));
    }

    // Random không trùng
    public static class RandomPicker<T> {

        private final List<T> items;
        private final List<Integer> remainingIndexes;
        private final Random rand;

        public RandomPicker(List<T> items) {
            this.items = items;
            this.rand = new Random();
            this.remainingIndexes = new ArrayList<>();

            for (int i = 0; i < items.size(); i++) {
                remainingIndexes.add(i);
            }
        }

        public T pick() {
            if (remainingIndexes.isEmpty()) {
                throw new RuntimeException("No items left");
            }

            int pos = rand.nextInt(remainingIndexes.size());
            int index = remainingIndexes.remove(pos);

            return items.get(index);
        }
    }
}