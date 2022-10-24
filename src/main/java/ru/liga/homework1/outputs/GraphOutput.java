package ru.liga.homework1.outputs;

import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;

import java.util.List;

/**
 * Вывод результата в виде графика
 */
public class GraphOutput implements Output {
    @Override
    public void doOutput(List<CurrencyData> predictionResult) throws InvalidOutputParametersException {
        if (predictionResult.size() == 0)
            throw new InvalidOutputParametersException("Нет данных");
        if (predictionResult.size() > 1) {
            int size = predictionResult.get(0).getData().size();
            if (predictionResult.stream().skip(1).anyMatch(item -> item.getData().size() != size))
                throw new InvalidOutputParametersException("Для всех валют должны быть одинаковые размерности массивов");
        }
        //??
    }
}
