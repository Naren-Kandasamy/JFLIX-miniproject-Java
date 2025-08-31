package EXCEPTIONS;

public class AuthorizationException extends Exception{
    public AuthorizationException(){
        super("Authorization failed, user does not have sufficient priviledges");
    }

    public AuthorizationException(String message){
        super(message);
    }

    public AuthorizationException(String message, Throwable cause){
        super(message, cause);
    }
}
