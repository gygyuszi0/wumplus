package hu.nye.progtech.wumplus.service.map;

import hu.nye.progtech.wumplus.model.MapVO;

/**
 * Map ellenőrzását szolgáló interface, nem biztos, hogy ez lesz használva.
 *
 * @// TODO: 2023. 11. 09. Ez kell-e még?
 */

public interface MapValidator {
    public Boolean validateMap(MapVO mapVO);
}
