package exception;

public class PublicacaoException extends Exception {
    public PublicacaoException(String message) {
        super(message);
    }

    public PublicacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
