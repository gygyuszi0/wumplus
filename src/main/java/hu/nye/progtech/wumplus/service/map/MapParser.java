package hu.nye.progtech.wumplus.service.map;

import hu.nye.progtech.wumplus.model.Map;

import java.util.List;

public interface MapParser {
    Map parseMap(List<String> rawMap);
}
