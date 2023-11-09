package hu.nye.progtech.wumplus.service.map;

/**
 * MapValidátor által dopott kivételek.
 */
public class MapValidatorNotSquerException extends Throwable {
    public MapValidatorNotSquerException() {
    }

    public MapValidatorNotSquerException(String message) {
        super("The map is not a sqare");
    }
}
