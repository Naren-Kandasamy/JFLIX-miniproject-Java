package SERVICES;

import USER.User;
import EXCEPTIONS.AuthenticationException;
import EXCEPTIONS.DuplicateUserException;
import EXCEPTIONS.UserNotFoundException;

import java.util.*;

public class AuthSystem {
    private Map<String,String> credentials;

    public AuthSystem(){
        this.credentials = new HashMap<>();
    }

    public void register(User user, String password) throws DuplicateUserException{
        if(credentials.containsKey(user.getID())){
            throw new DuplicateUserException("User with "+user.getID()+" already exists");
        }

        credentials.put(user.getID(),password);
        System.out.println("Registered Successfully");
    }

    public boolean login(User user, String password) throws UserNotFoundException, AuthenticationException{
        if(!credentials.containsKey(user.getID())){
            throw new UserNotFoundException("User "+user.getID()+" not found");
        }

        String stored_password = credentials.get(user.getID());
        if(!password.equals(stored_password)){
            throw new AuthenticationException("Invalid password for user "+user.getID());
        }

        return true;
    }

    public void remove_user(User user) throws UserNotFoundException{
        if(!credentials.containsKey(user.getID())){
            throw new UserNotFoundException("User "+user.getID()+" not found");
        }

        credentials.remove(user.getID());
        System.out.println("User removed successfully");
    }

    public void listusers(){
        System.out.println("Registered users: " + credentials.keySet());
    }
}