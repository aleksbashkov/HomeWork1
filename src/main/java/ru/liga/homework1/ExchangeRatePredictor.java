package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ru.liga.homework1.Enums.Period;

public class ExchangeRatePredictor {

    final List<Map.Entry<LocalDate, BigDecimal>> incomingData;

    /**
     * Конструктор
      * @param exchangeRateData - входные данные курсов валюты
     */
    public ExchangeRatePredictor(List<Map.Entry<LocalDate, BigDecimal>> exchangeRateData) {
        incomingData = exchangeRateData;
    }

    /**
     * Осуществляет прогнозирование курса валюты
      * @param algorithm алгоритм, который осуществляет прогнозирование
     * @param period - период, на который осуществляется прогнозирование
     * @return - прогноз в виде списка пар (дата, курс валюты)
     */
    public List<Map.Entry<LocalDate, BigDecimal>> doPrediction(PredictionAlgorithm algorithm, Period period) {
        var result = new ArrayList<Map.Entry<LocalDate, BigDecimal>>();

        var values = new ArrayList<>(incomingData.stream().map(Map.Entry::getValue).toList()); // только курсы, без привязки к датам
        var tomorrow = LocalDate.now().plusDays(1);
        var last = incomingData.get(incomingData.size()-1);
        BigDecimal tomorrowValue;
        if (last.getKey().equals(tomorrow)) { // завтрашний курс уже может быть во входящих данных, тогда его прогнозировать не надо
            tomorrowValue = last.getValue();
        }
        else {
            tomorrowValue = algorithm.doPrediction(values);
            values.add(tomorrowValue);
        }
        result.add(new AbstractMap.SimpleImmutableEntry<>(tomorrow, tomorrowValue)); // добавляем в результат значение на завтра

        if (period == Period.WEEK) { // если нужен прогноз на неделю, то делаем прогнозирование ещё на 6 дней
            for (int i = 1; i < 7; i++) {
                var newValue = algorithm.doPrediction(values);
                result.add(new AbstractMap.SimpleImmutableEntry<>(tomorrow.plusDays(i), newValue));
                values.add(newValue);
            }
        }

        return result;
    }
}
