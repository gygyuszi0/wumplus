package hu.nye.progtech.wumplus.service.exception;

/**
 * Map konvertálása közben előforduló hibák kivételei.
 */
public class MapParseException extends Exception {

    public MapParseException(String message) {
        super(message);
    }

}
