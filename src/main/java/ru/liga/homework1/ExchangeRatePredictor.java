package ru.liga.homework1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ru.liga.homework1.algorithms.PredictionAlgorithm;
import ru.liga.homework1.model.RateForDate;

/**
 * Класс осуществляет прогнозирование курсов в соответствии с переданным ему в конструктор алгоритмом
 */
public class ExchangeRatePredictor {

    final PredictionAlgorithm algorithm;

    public ExchangeRatePredictor(PredictionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Прогнозирование курсов валют по исходным данным на переданные даты
     * @param incomingData - входные данные с курсами
     * @param dates - список дат, на которые делается прогноз
     * @return прогноз в виде списка объектов типа RateForDate
     */
    public List<RateForDate> doPrediction(final List<RateForDate> incomingData, final List<LocalDate> dates) {
        var result = new ArrayList<RateForDate>();
        for (var date : dates)
            result.add(new RateForDate(date, algorithm.doPrediction(incomingData, date)));
        return result;
    }
}
