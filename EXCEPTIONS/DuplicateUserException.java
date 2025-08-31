package EXCEPTIONS;

public class DuplicateUserException extends Exception{
    public DuplicateUserException(){
        super("A user with this name already exists");
    }

    public DuplicateUserException(String message){
        super(message);
    }

    public DuplicateUserException(String message, Throwable cause){
        super(message, cause);
    }
}