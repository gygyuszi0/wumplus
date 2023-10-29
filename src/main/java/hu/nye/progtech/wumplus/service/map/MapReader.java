package hu.nye.progtech.wumplus.service.map;

import java.util.List;

public interface MapReader {
    public List<String> readMap() throws MapReadException;
}
