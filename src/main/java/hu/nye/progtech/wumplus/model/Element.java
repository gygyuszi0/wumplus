package hu.nye.progtech.wumplus.model;

import java.util.List;

/**
 * Map lehetséges elemei.
 */
public class Element {
    public static final char WALL = 'W';
    public static final char HERO = 'H';
    public static final char WUMP = 'U';

    public static final char PIT = 'P';
    public static final char GOLD = 'G';
    public static final char SPACE = '_';
    public static final String VALID_ROW_REGEX = String.format("[%c,%c,%c,%c,%c,%c]+", WALL, HERO, WUMP, PIT, GOLD, SPACE);

    public static final List<Character> STATIC_ELEMENT = List.of(WALL, PIT);
    public static final List<Character> NONSTATIC_ELEMENT = List.of(HERO, WUMP, GOLD, SPACE);

    private Element() {
    }
}
