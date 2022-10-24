package ru.liga.homework1.exceptions;

/**
 * Исключение, выкидываемое в случае неверных данных для вывода результатав прогноза
 */
public class InvalidOutputParametersException extends Exception {
    public InvalidOutputParametersException(String message) {
        super(message);
    }
}
