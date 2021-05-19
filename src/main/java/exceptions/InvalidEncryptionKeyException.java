package exceptions;

public class InvalidEncryptionKeyException extends Exception {

    private static final String basicMessage = "the KEY to DECRYPT with, from the FILE, isn't proper. ";

    public InvalidEncryptionKeyException(String exceptionReason) {
        super(basicMessage + exceptionReason);
    }
}
