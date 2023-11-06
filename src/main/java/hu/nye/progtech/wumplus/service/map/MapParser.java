package hu.nye.progtech.wumplus.service.map;

import java.util.List;

import hu.nye.progtech.wumplus.model.MapVO;

/**
 * Raw map konvertálása value object-té.
 */
public interface MapParser {
    MapVO parseMap(List<String> rawMap);
}
