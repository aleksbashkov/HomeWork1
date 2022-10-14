package ru.liga.homework1.algorithms;

import ru.liga.homework1.RateForDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Интерфейс для алгоритмов прогнозирования
 */
@FunctionalInterface
public interface PredictionAlgorithm {
    /**
     * Прогнозирующий метод
     * @param exchangeRates упорядоченные по дате курсы валюты
     * @return следующее прогнозируемое значение
     */
    BigDecimal doPrediction(final List<RateForDate> exchangeRates, LocalDate dateForPrediction);
}
