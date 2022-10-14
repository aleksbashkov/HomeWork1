package ru.liga.homework1.algorithm;

import ru.liga.homework1.RateForDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        var firstDate = exchangeRates.get(0).getDate();
        var lastDate = exchangeRates.get(exchangeRates.size()-1).getDate();
        LocalDate date = LocalDate.of(firstDate.getYear(), dateForPrediction.getMonthValue(), dateForPrediction.getDayOfMonth());
        if (date.isBefore(firstDate))
            date = date.plusYears(1);
        while (!date.isAfter(lastDate)) {
            var index = ChronoUnit.DAYS.between(firstDate, date);
            pastValues.add(exchangeRates.get((int)index).getRate());
            date = date.plusYears(1);
        }
        if (pastValues.size() == 0)
            throw  new RuntimeException(String.format("В исходных данных не найдены курсы за %d %s", date.getDayOfMonth(), date.getMonth()));

        int randomIndex = new Random().nextInt(pastValues.size());
        return pastValues.get(randomIndex);
    }
}
