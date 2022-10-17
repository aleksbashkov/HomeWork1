package ru.liga.homework1;

import ru.liga.homework1.enums.*;
import ru.liga.homework1.exceptions.InvalidCommandException;
import ru.liga.homework1.model.CommandParameters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Разбирает введённую пользователем строку.
 */
public class CommandParser {

    private static final String cmdRatePart = "rate ";
    private static final String cmdAlgPart = " -alg ";
    private static final String cmdDatePart = " -date ";
    private static final String cmdPeriodPart = " -period ";
    private static final String cmdOutputPart = " -output ";
    private static final String cmdTomorrowPart = "tomorrow";
    private static final String cmdTemplate = "rate Cur[,CurN] (-data (tomorrow|concrete_date) | -period (week|month)) -alg (lastyear|myst|linear) [-output (list|graph)]";

    /**
     * Парсит команду
     * @throws InvalidCommandException - кидает исключение InvalidCommandException в случае неверной команды
     */
    public static CommandParameters ParseCommand(String command) throws InvalidCommandException {
        int algPartIndex = command.indexOf(cmdAlgPart);
        if (!command.startsWith(cmdRatePart) || algPartIndex == -1)
            throw new InvalidCommandException("Неверная команда. Команда должна иметь вид " + cmdTemplate);
        String beforeAlgStr = command.substring(0, algPartIndex);
        int datePartIndex = beforeAlgStr.indexOf(cmdDatePart);
        int periodPartIndex = beforeAlgStr.indexOf(cmdPeriodPart);
        if ((datePartIndex != -1 && periodPartIndex != -1) || (datePartIndex == -1 && periodPartIndex == -1))
            throw new InvalidCommandException("Должна быть указана дата ИЛИ период для прогноза. Команда должна иметь вид " + cmdTemplate);

        String currenciesStr;
        Period period = null;
        LocalDate date = null;
        if (datePartIndex != -1) { // date
            String dateStr = beforeAlgStr.substring(datePartIndex + cmdDatePart.length(), algPartIndex);
            date = dateStr.equals(cmdTomorrowPart)
                ? LocalDate.now().plusDays(1)
                : LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            currenciesStr = beforeAlgStr.substring(cmdRatePart.length(), datePartIndex);
        }
        else { // period
            String periodStr = beforeAlgStr.substring(periodPartIndex + cmdPeriodPart.length(), algPartIndex);
            if (!Period.checkPeriodName(periodStr.toUpperCase()))
                throw new InvalidCommandException("Неизвестный период " + periodStr);
            period = Period.valueOf(periodStr.toUpperCase());
            currenciesStr = beforeAlgStr.substring(cmdRatePart.length(), periodPartIndex);
        }

        // parse currencies:
        var curEntities = currenciesStr.split(",");
        if (curEntities.length == 0)
            throw  new InvalidCommandException("Не заданы валюты");
        var currencies = new ArrayList<Currency>();
        for (var cur : curEntities)
            if (!Currency.checkCurrencyName(cur.toUpperCase()))
                throw new InvalidCommandException("Неизвестная валюта " + cur);
            else
                currencies.add(Currency.valueOf(cur));

        String afterAlgStr = command.substring(algPartIndex + cmdAlgPart.length());
        int outputPartIndex = afterAlgStr.indexOf(cmdOutputPart);

        // parse algorithm:
        Algorithm algorithm;
        String algStr = (outputPartIndex == -1) ? afterAlgStr : afterAlgStr.substring(0, outputPartIndex);
        if (!Algorithm.checkAlgorithmName(algStr.toUpperCase()))
            throw new InvalidCommandException("Неизвестный алгоритм " + algStr);
        algorithm = Algorithm.valueOf(algStr.toUpperCase());

        // parse output type:
        OutputType outputType = OutputType.LIST;
        if (outputPartIndex != -1) {
            String outputTypeStr = afterAlgStr.substring(outputPartIndex + cmdOutputPart.length());
            if (!OutputType.checkOutputTypeName(outputTypeStr.toUpperCase()))
                throw new InvalidCommandException("Неизвестный тип вывода прогноза " + outputTypeStr);
            outputType = OutputType.valueOf(outputTypeStr.toUpperCase());
        }

        // если несколько валют, то д.б. график
        if (currencies.size() > 1 && outputType == OutputType.LIST)
            throw new InvalidCommandException("Для нескольких валют в качестве типа вывода результатов ожидается график");

        // если одна дата, то д.б. одна валюта
        if (date != null && currencies.size() > 1)
            throw new InvalidCommandException("Для одной даты ожидается только одна валюта");

        if (date != null && outputType == OutputType.GRAPH)
            throw new InvalidCommandException("Для одной даты в качестве типа вывода результатов график не ожидается");

        return  new CommandParameters(currencies, period, date, algorithm, outputType);
    }
}
