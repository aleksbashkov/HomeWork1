package ru.liga.homework1.enums;

import java.util.Arrays;

/**
 * Тип вывода результата прогноза
 */
public enum OutputType {
    LIST,
    GRAPH;

    public static boolean checkOutputType(String outputTypeName) {
        return Arrays.stream(OutputType.values()).anyMatch(item -> item.name().equals(outputTypeName));
    }
}
