package hu.nye.progtech.wumplus.service.exception;

/**
 * PlayerVo készítésekor előforduló hibák.
 */
public class PlayerParserException extends Exception {
    public PlayerParserException() {
    }

    public PlayerParserException(String message) {
        super(message);
    }
}
