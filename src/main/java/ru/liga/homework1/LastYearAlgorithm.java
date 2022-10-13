package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Алгоритм, выдающий в качестве прогноза значение курса год назад
 */
public class LastYearAlgorithm implements PredictionAlgorithm {
    @Override
    public BigDecimal doPrediction(List<RateForDate> exchangeRates, LocalDate dateForPrediction) {
        return exchangeRates.stream()
                .filter(rate -> rate.getDate().equals(dateForPrediction.minusYears(1)))
                .findFirst()
                .get()
                .getRate();
    }
}
