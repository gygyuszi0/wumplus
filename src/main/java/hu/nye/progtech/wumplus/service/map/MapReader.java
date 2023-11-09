package hu.nye.progtech.wumplus.service.map;

import java.util.List;

import hu.nye.progtech.wumplus.service.exception.MapReadException;

/**
 * Map beolvasása.
 */
public interface MapReader {

    List<String> readMap() throws MapReadException;

}