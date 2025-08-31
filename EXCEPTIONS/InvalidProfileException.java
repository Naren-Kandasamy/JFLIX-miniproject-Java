package EXCEPTIONS;

public class InvalidProfileException extends Exception{
    public InvalidProfileException(){
        super("Invalid profile!, please check profile details");
    }

    public InvalidProfileException(String message){
        super(message);
    }

    public InvalidProfileException(String message, Throwable cause){
        super(message, cause);
    }
}
