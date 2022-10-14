package ru.liga.homework1.algorithms;

import ru.liga.homework1.RateForDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Алгоритм, выдающий в качестве прогноза значение курса в этот день в прошлом году
 */
public class LastYearAlgorithm implements PredictionAlgorithm {
    @Override
    public BigDecimal doPrediction(List<RateForDate> exchangeRates, LocalDate dateForPrediction) {
        var date = LocalDate.of(LocalDate.now().getYear()-1, dateForPrediction.getMonthValue(), dateForPrediction.getDayOfMonth());
        var firstDate = exchangeRates.get(0).getDate();
        var lastDate = exchangeRates.get(exchangeRates.size()-1).getDate();
        if (date.isBefore(firstDate) || date.isAfter(lastDate))
            throw  new RuntimeException("Курс за используемую в прогнозе дату " + date + " не найден в исходных данных");
        var index = ChronoUnit.DAYS.between(firstDate, date);
        return exchangeRates.get((int) index).getRate();
    }
}
