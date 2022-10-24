package ru.liga.homework1.outputs;

import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;

import java.util.List;

/**
 * Вывод результата в консоль
 */
public class ListOutput implements Output {
    @Override
    public void doOutput(List<CurrencyData> predictionResult) throws InvalidOutputParametersException {
        if (predictionResult.size() == 0)
            throw new InvalidOutputParametersException("Нет данных");
        if (predictionResult.size() > 1)
            throw new InvalidOutputParametersException("Вывод в консоль предполагает данные только по одной валюте");
        var data = predictionResult.get(0).getData();
        for (var rate : data)
            System.out.printf("%1$ta %1$td.%1$tm.%1$tY - %2$.2f%n", rate.getDate(), rate.getRate());
    }
}
