package hu.nye.progtech.wumplus.model;

import java.util.List;

/**
 * Map object.
 *
 * Lépés után egy új map keletkezik.
 */

public class Map {
    private final Integer numberOfRows;
    private final Integer numberOfCols;
    private final Integer heroX;
    private final Integer heroY;
    private final List<String> mapObjects;

    private final List<Boolean> mapFixed;

    public Map(Integer numberOfRows, Integer numberOfCols, Integer heroX, Integer heroY, List<String> mapObjects, List<Boolean> mapFixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfCols = numberOfCols;
        this.heroX = heroX;
        this.heroY = heroY;
        this.mapObjects = mapObjects;
        this.mapFixed = mapFixed;
    }
}
