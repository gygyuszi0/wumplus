package hu.nye.progtech.wumplus.service.exception;

/**
 * Mappa olvasásakor fellépő hibák kivételei.
 */
public class MapReadException extends Exception {

    public MapReadException(String message) {
        super(message);
    }

}