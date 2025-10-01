package EXCEPTIONS;

public class DuplicateContentException extends Exception{
    public DuplicateContentException(){
        super("A user with this name already exists");
    }

    public DuplicateContentException(String message){
        super(message);
    }

    public DuplicateContentException(String message, Throwable cause){
        super(message, cause);
    }
}