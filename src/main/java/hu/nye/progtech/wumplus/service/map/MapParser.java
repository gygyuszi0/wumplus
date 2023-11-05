package hu.nye.progtech.wumplus.service.map;

import hu.nye.progtech.wumplus.model.MapVO;

import java.util.List;

public interface MapParser {
    MapVO parseMap(List<String> rawMap);
}
