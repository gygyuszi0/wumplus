package hu.nye.progtech.wumplus.service.map;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;

/**
 * Map logikai helyességét ellenőrzi.
 */
public interface MapValidator {
    public Boolean validateMap(MapVO mapVO) throws MapValidationException;
}