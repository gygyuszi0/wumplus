package hu.nye.progtech.wumplus.service.exception;

/**
 * Map ellenőrzése során előforduló hibák.
 */
public class MapValidationException extends Exception {

    public MapValidationException(String message) {
        super(message);
    }

}
