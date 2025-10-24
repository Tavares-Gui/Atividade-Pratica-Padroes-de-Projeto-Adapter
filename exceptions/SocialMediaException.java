package exceptions;

public class SocialMediaException extends Exception {
    public SocialMediaException(String message) { super(message); }
    public SocialMediaException(String message, Throwable cause) { super(message, cause); }
}
