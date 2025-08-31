package EXCEPTIONS;

public class InvalidRatingException extends Exception{
    public InvalidRatingException() {
        super("Invalid rating! Rating must be between 1 and 5.");
    }

    public InvalidRatingException(String message){
        super(message);
    }

    public InvalidRatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
