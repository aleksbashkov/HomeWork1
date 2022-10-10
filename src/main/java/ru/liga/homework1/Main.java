package ru.liga.homework1;

import ru.liga.homework1.exceptions.InvalidCommandException;

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

        // получаем исходные данные:
        var exchangeRateData = ExchangeRateDataProvider.getSortedExchangeRateData(parameters.getCurrency());

        // прогнозируем курс валюты на завтра или на следующую неделю алгоритмом AverageByLastSevenValues:
        var predictor = new ExchangeRatePredictor(exchangeRateData);
        var result = predictor.doPredictionForPeriod(new AverageByLastSevenValues(), parameters.getPeriod());

        // выводим результат в консоль, используя форматтер SimpleFormatter:
        printPredictionResult(result, new SimpleFormatter());
    }

    private static void printPredictionResult(List<RateForDate> prediction, ExchangeRateFormatter formatter) {
        Collections.reverse(prediction);
        prediction
            .forEach(rate -> System.out.println(formatter.formatCurrencyRate(rate)));
    }
}