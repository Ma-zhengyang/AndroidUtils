package com.android.mazhengyang.library;

import java.util.Random;

/**
 * Created by mazhengyang on 18-11-20.
 */

public class RandomUtils {

    public static int randomInt() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int randomInt(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }


    public static float randomFloat() {
        Random random = new Random();
        return random.nextFloat();
    }

    public static double randomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    public static long randomLong() {
        Random random = new Random();
        return random.nextLong();
    }

    public static boolean randomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static double randomGaussian() {
        Random random = new Random();
        return random.nextGaussian();
    }

    public static void randomBytes(byte[] buf) {
        Random random = new Random();
        random.nextBytes(buf);
    }
}
