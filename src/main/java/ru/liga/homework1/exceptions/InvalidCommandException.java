package ru.liga.homework1.exceptions;

/**
 * Исключение, выкидываемое в случае неверной команды
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
