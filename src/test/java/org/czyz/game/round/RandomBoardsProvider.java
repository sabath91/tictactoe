package org.czyz.game.round;

import org.testng.annotations.DataProvider;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomBoardsProvider {

    @DataProvider
    public static Object[] randomBoards() {
        final int bound = 100;
        return IntStream.generate(() -> randomPositiveInt(bound))
                .limit(bound)
                .mapToObj(height -> {
                    int width = randomPositiveInt(bound);
                    int lengthValue = randomPositiveInt(Math.min(height, width));
                    return new Triplet(height, width, lengthValue);
                })
                .collect(Collectors.toList())
                .toArray();
    }

    private static int randomPositiveInt(final int bound) {
        Random rn = new Random();
        int range = bound - 3 + 1;
        return rn.nextInt(range) + 3;
    }
}
