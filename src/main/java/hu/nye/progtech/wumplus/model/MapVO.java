package hu.nye.progtech.wumplus.model;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

/**
 * Map object.
 *
 * Lépés után egy új map keletkezik.
 */

public class MapVO {
    private final Integer numberOfRows;
    private final Integer numberOfCols;
    private final Integer heroX;
    private final Integer heroY;
    private final List<String> mapObjects;

    private final List<List<Boolean>> mapFixed;

    public MapVO(Integer numberOfRows, Integer numberOfCols, Integer heroX, Integer heroY, List<String> mapObjects, List<List<Boolean>> mapFixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfCols = numberOfCols;
        this.heroX = heroX;
        this.heroY = heroY;
        this.mapObjects = mapObjects;
        this.mapFixed = mapFixed;
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfCols=" + numberOfCols +
                ", heroX=" + heroX +
                ", heroY=" + heroY +
                ", mapObjects=" + mapObjects +
                ", mapFixed=" + mapFixed +
                '}';
    }
}
