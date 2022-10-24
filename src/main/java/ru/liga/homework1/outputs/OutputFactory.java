package ru.liga.homework1.outputs;

import ru.liga.homework1.enums.OutputType;

public class OutputFactory {

    public static Output getOutput(OutputType outputType) {
        return switch (outputType) {
            case LIST -> new ListOutput();
            case GRAPH -> new GraphOutput();
        };
    }
}
