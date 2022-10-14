package ru.liga.homework1.algorithms;

import ru.liga.homework1.RateForDate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

/**
 * Алгоритм, прогнозирующий следующее значение как среднее по семи предыдущим
 */
public class AverageByLastSevenValues implements PredictionAlgorithm {

    final int AVERAGE_WINDOW = 7;

    @Override
    public BigDecimal doPrediction(final List<RateForDate> exchangeRates, LocalDate dateForPrediction) {
        int cnt = exchangeRates.size();
        if (cnt < AVERAGE_WINDOW)
            throw new InvalidParameterException("В списке курсов слишком мало значений");
        BigDecimal sum = new BigDecimal(0);
        for (int i = cnt-1; i >= cnt-AVERAGE_WINDOW; i--)
            sum = sum.add(exchangeRates.get(i).getRate());
        return sum.divide(BigDecimal.valueOf(AVERAGE_WINDOW), RoundingMode.HALF_UP);
    }
}
