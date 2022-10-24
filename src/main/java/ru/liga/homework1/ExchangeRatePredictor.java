package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ru.liga.homework1.algorithms.PredictionAlgorithm;
import ru.liga.homework1.enums.Period;
import ru.liga.homework1.model.RateForDate;

public class ExchangeRatePredictor {

    final PredictionAlgorithm algorithm;

    public ExchangeRatePredictor(PredictionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

//    /**
//     * Осуществляет прогнозирование курса валюты
//      * @param algorithm алгоритм, который осуществляет прогнозирование
//     * @param period - период, на который осуществляется прогнозирование
//     * @return - прогноз в виде списка объектов типа RateForDate
//     */
    public List<RateForDate> doPrediction(List<RateForDate> incomingData, Period period) {
        var result = new ArrayList<RateForDate>();

        var tomorrow = LocalDate.now().plusDays(1);
        var last = incomingData.get(incomingData.size()-1);
        BigDecimal tomorrowValue;
        if (last.getDate().equals(tomorrow)) { // завтрашний курс уже может быть во входящих данных, тогда его прогнозировать не надо
            tomorrowValue = last.getRate();
        }
        else {
            tomorrowValue = algorithm.doPrediction(incomingData, tomorrow);
            incomingData.add(new RateForDate(tomorrow, tomorrowValue));
        }
        result.add(new RateForDate(tomorrow, tomorrowValue)); // добавляем в результат значение на завтра

        if (period == Period.WEEK) { // если нужен прогноз на неделю, то делаем прогнозирование ещё на 6 дней
            for (int i = 1; i < 7; i++) {
                var newDate = tomorrow.plusDays(i);
                var newValue = algorithm.doPrediction(incomingData, newDate);
                var newRate = new RateForDate(newDate, newValue);
                result.add(newRate);
                incomingData.add(newRate);
            }
        }

        return result;
    }
}
