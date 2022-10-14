package ru.liga.homework1.algorithms;

import ru.liga.homework1.enums.Algorithm;

public class AlgorithmFactory {

    public static PredictionAlgorithm getAlgorithm(Algorithm algorithm) {
        return switch (algorithm) {
            case MYST -> new MysticalAlgorithm();
            case LASTYEAR -> new LastYearAlgorithm();
            case LINEAR -> new LinearAlgorithm();
        };
    }
}
