package ru.liga.homework1;

import ru.liga.homework1.algorithms.AlgorithmFactory;
import ru.liga.homework1.exceptions.InvalidCommandException;
import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;
import ru.liga.homework1.outputs.OutputFactory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean isCommandValid;
        do {
            try {
                doExchangeRatePrediction();
                isCommandValid = true;
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println("Попробуйте ещё раз.");
                isCommandValid = false;
            }
        } while (!isCommandValid);
    }

    private static void doExchangeRatePrediction() throws InvalidCommandException {
        System.out.println("Введите команду:");

        // парсим введёную пользователем команду и добываем оттуда параметры:
        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        var parameters = CommandParser.ParseCommand(cmd);

        var predictor = new ExchangeRatePredictor(AlgorithmFactory.getAlgorithm(parameters.getAlgorithm()));
        var predictionResult = new ArrayList<CurrencyData>();
        for (var currency : parameters.getCurrencies()) {
            // получаем исходные данные:
            var exchangeRateData = ExchangeRateDataProvider.getSortedExchangeRateData(currency);

            // прогнозируем курсы:
            var result = predictor.doPrediction(exchangeRateData, parameters.getPeriod());//?? getDate?
            predictionResult.add(new CurrencyData(currency, result));
        }

        var output = OutputFactory.getOutput(parameters.getOutputType());
        try {
            output.doOutput(predictionResult);
        } catch (InvalidOutputParametersException e) {
            //??
        }

    }
}