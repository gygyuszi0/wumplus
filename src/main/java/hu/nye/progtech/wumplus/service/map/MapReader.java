package hu.nye.progtech.wumplus.service.map;

import java.util.List;

/**
 * Map olvasást végző interface.
 */
public interface MapReader {
    public List<String> readMap() throws MapReadException;
}
