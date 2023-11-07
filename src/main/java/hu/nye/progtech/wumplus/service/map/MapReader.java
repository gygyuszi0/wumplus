package hu.nye.progtech.wumplus.service.map;

import hu.nye.progtech.wumplus.service.exception.MapReadException;

import java.util.List;

public interface MapReader {

    List<String> readMap() throws MapReadException;

}