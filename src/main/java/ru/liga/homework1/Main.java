package ru.liga.homework1;

import ru.liga.homework1.Enums.Period;
import ru.liga.homework1.Exceptions.InvalidCommandException;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean isCommandValid = true;
        do {
            try {
                doExchangeRatePrediction();
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
                System.out.println("Try one more time.");
                isCommandValid = false;
            }
        } while (isCommandValid);
    }

    private static void doExchangeRatePrediction() throws InvalidCommandException {
        System.out.println("Enter command:");

        // парсим введёную пользователем команду:
        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        var cmdParser = new CommandParser(cmd);
        var period = cmdParser.getPeriod();
        var currency = cmdParser.getCurrency();

        // выводим курс валюты на завтра или на следующую неделю:
        var predictor = new ExchangeRatePredictor(new AverageByLastSevenValues(), new SimpleFormatter());
        if (period == Period.TOMORROW)
            predictor.printTomorrowExchangeRate(currency);
        else
            predictor.printNextWeekExchangeRate(currency);
    }
}