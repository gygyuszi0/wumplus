package hu.nye.progtech.wumplus.service.exception;

/**
 * Kivétel, ha a játékos meghal.
 */
public class PlayerDeadException extends Exception {
    public PlayerDeadException(String message) {
        super(message);
    }
}
