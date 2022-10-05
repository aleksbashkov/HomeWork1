package ru.liga.homework1;

import ru.liga.homework1.Enums.Period;
import ru.liga.homework1.Exceptions.InvalidCommandException;

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

        // парсим введёную пользователем команду:
        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        var parameters = CommandParser.ParseCommand(cmd);
        System.out.println(parameters);

        // выводим курс валюты на завтра или на следующую неделю:
        var predictor = new ExchangeRatePredictor(new AverageByLastSevenValues(), new SimpleFormatter());
        if (parameters.period == Period.TOMORROW)
            predictor.printTomorrowExchangeRate(parameters.currency);
        else
            predictor.printNextWeekExchangeRate(parameters.currency);
    }
}