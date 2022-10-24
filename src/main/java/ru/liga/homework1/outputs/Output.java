package ru.liga.homework1.outputs;

import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;

import java.util.List;

/**
 * Интерфейс для вывода результатов прогноза
 */
@FunctionalInterface
public interface Output {
    /**
     * Процедура вывода результатов прогноза
     * @param predictionResult набор валют и прогнозных значений
     */
    void doOutput(final List<CurrencyData> predictionResult) throws InvalidOutputParametersException;
}
