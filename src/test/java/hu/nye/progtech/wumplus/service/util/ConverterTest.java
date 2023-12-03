package hu.nye.progtech.wumplus.service.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private static final Character A = 'A';
    private static final Character Z = 'Z';
    private static final Character F = 'F';

    @BeforeEach
    void setUp() {
    }

    @Test
    void letterToInteger() {
        System.out.println("[TEST\t] : Convert some charcter to int");
        System.out.println("\t\t\tGIVEN\t:" + A + Z + F);
        Integer resultA = Converter.letterToInteger(A);
        Integer resultZ = Converter.letterToInteger(Z);
        Integer resultF = Converter.letterToInteger(F);
        System.out.println("\t\t\tWHEN\t:" + resultA + " " + resultZ + " " + resultF);

        Assertions.assertEquals(resultA, 1);
        Assertions.assertEquals(resultZ, 26);
        Assertions.assertEquals(resultF, 6);

    }
}