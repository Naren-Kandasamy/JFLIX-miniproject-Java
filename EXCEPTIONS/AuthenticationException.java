package EXCEPTIONS;

public class AuthenticationException extends Exception{
        public AuthenticationException(){
            super("Authentication failed, invalid credentials");
        }

        public AuthenticationException(String message){
            super(message);
        }

        public AuthenticationException(String message, Throwable cause){
            super(message,cause);
        }
}
