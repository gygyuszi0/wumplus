package hu.nye.progtech.wumplus.service.exception;

/**
 * Parancs végrehajtása során előforduló kivételek.
 */
public class PerformerException extends Exception {
    public PerformerException(String message) {
        super(message);
    }
}
