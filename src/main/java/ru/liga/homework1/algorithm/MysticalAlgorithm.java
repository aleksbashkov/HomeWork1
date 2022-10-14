package ru.liga.homework1.algorithm;

import ru.liga.homework1.RateForDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Алгоритм, выдающий в качестве прогноза рандомное значение курсов на этот день среди всех предыдущих лет
 */
public class MysticalAlgorithm implements PredictionAlgorithm {
    @Override
    public BigDecimal doPrediction(List<RateForDate> exchangeRates, LocalDate dateForPrediction) {
        var pastValues = new ArrayList<BigDecimal>();
        var earliestDate = exchangeRates.get(0).getDate();
        for (LocalDate date = dateForPrediction.minusYears(1); !date.isBefore(earliestDate); date = date.minusYears(1)) {
            LocalDate curDate = date;
            pastValues.add(
                    exchangeRates.stream()
                        .filter(rate -> rate.getDate().equals(curDate))
                        .findFirst()
                        .get()
                        .getRate());
        }
        int randomIndex = new Random().nextInt(pastValues.size());
        return pastValues.get(randomIndex);
    }
}
