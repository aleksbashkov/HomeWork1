package ru.liga.homework1.outputs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.liga.homework1.exceptions.InvalidOutputParametersException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphOutputTest {

    @DisplayName("Кидает исключение, когда в качестве данных передаётся null")
    @Test
    void shouldThrowExceptionIfNullData() {
        var graph = new GraphOutput();
        assertThrows(InvalidOutputParametersException.class, () -> graph.doOutput(null));
    }

    @DisplayName("Кидает исключение, когда входные данные пусты")
    @Test
    void shouldThrowExceptionIfNoData() {
        var graph = new GraphOutput();
        assertThrows(InvalidOutputParametersException.class, () -> graph.doOutput(new ArrayList<>()));
    }
}