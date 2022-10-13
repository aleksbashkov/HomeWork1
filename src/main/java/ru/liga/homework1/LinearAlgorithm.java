package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Алгоритм выдаёт прогноз на основе линейной регресии, построенной по данным последнего месяца
 */
public class LinearAlgorithm implements PredictionAlgorithm {

    class  RegressionCoefficients {
        private final double linearCoefficient;
        private final double additionalCoefficient;

        RegressionCoefficients(double linearCoefficient, double additionalCoefficient) {
            this.linearCoefficient = linearCoefficient;
            this.additionalCoefficient = additionalCoefficient;
        }
    }
    @Override
    public BigDecimal doPrediction(List<RateForDate> exchangeRates, LocalDate dateForPrediction) {
        //RateForDate last = exchangeRates.get(exchangeRates.size()-1);
        var dayCount = exchangeRates.size();
        LocalDate latest = exchangeRates.get(dayCount-1).getDate();
        LocalDate monthBeforeLatest = latest.minusMonths(1);
        var modelDayCount = ChronoUnit.DAYS.between(monthBeforeLatest, latest); // число дней в месяце может быть разным, поэтому считаем его
        var lastMonthValues = exchangeRates.stream()
                .skip(dayCount-modelDayCount)
                .map(rate -> rate.getRate().doubleValue())
                .toList();
        var coefficients = getRegressionCoefficients(lastMonthValues);
        return new BigDecimal(coefficients.linearCoefficient * (ChronoUnit.DAYS.between(monthBeforeLatest, dateForPrediction)-1) + coefficients.additionalCoefficient);
    }

    private RegressionCoefficients getRegressionCoefficients(List<Double> values) {
        var cnt = values.size();
        long sumX = 0;
        long sumX2 = 0;
        double sumY = 0;
        double sumXY = 0;

        for (int i = 0; i < cnt; i++) {
            sumX += i;
            sumX2 += i*i;
            var curValue = values.get(i);
            sumY += curValue;
            sumXY += i*curValue;
        }
        double linear = (cnt*sumXY - sumX*sumY)/(cnt*sumX2 - sumX*sumX);
        return new RegressionCoefficients(linear, (sumY-linear*sumX)/cnt);
    }
}
