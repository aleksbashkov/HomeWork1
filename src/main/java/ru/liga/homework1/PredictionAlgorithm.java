package ru.liga.homework1;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс для алгоритмов прогнозирования
 */
@FunctionalInterface
interface PredictionAlgorithm {
    /**
     * Прогнозирующий метод
     * @param exchangeRates упорядоченные по дате курсы валюты
     * @return следующее прогнозируемое значение
     */
    BigDecimal doPrediction(final List<BigDecimal> exchangeRates);
}
