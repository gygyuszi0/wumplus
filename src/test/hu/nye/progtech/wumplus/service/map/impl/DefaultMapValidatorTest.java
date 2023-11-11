package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.MapVO;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMapValidatorTest {


    private static final char[][] MAP_CORRECT = {
        {'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', 'U', 'G', 'P', '_', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', '_', '_', 'P', '_', 'W'},
        {'W', 'W', 'W', 'W', 'W', 'W'}
    };
    private static final boolean[][] FIXED_CORRECT = {
        {true, true, true, true, true, true},
        {true, false, false, false, false, true},
        {true, false, false, true, false, true},
        {true, false, false, false, false, true},
        {true, false, false, true, false, true},
        {true, true, true, true, true, true}
    };
    
    private DefaultMapValidator underTest;
    @BeforeEach
    void setUp() {
        underTest = new DefaultMapValidator();
    }

    @Test
    void validateForCorrectMap() throws MapValidationException {
        System.out.println("[TEST\t] : Test case for correct map");
        // given
        MapVO givenMap = new MapVO(6,6, MAP_CORRECT, FIXED_CORRECT);
        System.out.println("\t\t\tGIVEN\t:" + givenMap);
        // when
        boolean result = underTest.validateMap(givenMap);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        assertEquals(result, true);
    }
}