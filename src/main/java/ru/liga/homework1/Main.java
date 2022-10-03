package ru.liga.homework1;

import ru.liga.homework1.Enums.Period;

import java.util.*;

public class Main {
    public static void main(String[] args) {
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