package EXCEPTIONS;

public class ContentNotFoundException extends Exception{
    public ContentNotFoundException(){
        super("Content not found");
    }    

    public ContentNotFoundException(String message){
        super(message);
    }

    public ContentNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
