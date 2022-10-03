package ru.liga.homework1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Алгоритм, прогнозирующий следующее значение как среднее по семи предыдущим
 */
public class AverageByLastSevenValues implements PredictionAlgorithm {

    final int AVERAGE_WINDOW = 7;

    @Override
    public BigDecimal doPrediction(final List<BigDecimal> exchangeRates) {
        int cnt = exchangeRates.size();
        if (cnt < AVERAGE_WINDOW)
            throw new InvalidParameterException("Incomplete exchange rate list");
        BigDecimal sum = new BigDecimal(0);
        for (int i = cnt-1; i >= cnt-AVERAGE_WINDOW; i--)
            sum = sum.add(exchangeRates.get(i));
        return sum.divide(BigDecimal.valueOf(AVERAGE_WINDOW), RoundingMode.HALF_UP);
    }
}
