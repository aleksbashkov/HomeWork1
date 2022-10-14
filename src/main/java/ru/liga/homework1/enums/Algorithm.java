package ru.liga.homework1.enums;

import java.util.Arrays;

/**
 * Алгоритмы
 */
public enum Algorithm {
    LASTYEAR,
    MYST,
    LINEAR;

    public static boolean checkAlgorithmName(String algorithmName) {
        return Arrays.stream(Algorithm.values()).anyMatch(alg -> alg.name().equals(algorithmName));
    }
}
