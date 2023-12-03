package hu.nye.progtech.wumplus.service.exception;

/**
 * Database műveletek hibája esetén dobódik.
 */
public class DBServiceException extends Exception {
    public DBServiceException(String message) {
        super(message);
    }
}
