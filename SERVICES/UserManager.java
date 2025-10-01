package SERVICES;

import USER.User;
import USER.Profile;
import EXCEPTIONS.*;

import java.util.*;
import java.util.stream.Collectors;

public class UserManager {
    private final UserRepository user_repository;
    private final AuthSystem auth_system;

    public UserManager(UserRepository user_repository, AuthSystem auth_system){
        this.user_repository = user_repository;
        this.auth_system = auth_system; 
    }

    public void add_user(User user, String password) throws DuplicateUserException{
        if(user_repository.exists(user.getID())){
            throw new DuplicateUserException("User "+user.getID()+" already exists");
        }

        user_repository.add(user);
        auth_system.register(user, password);
    }

    public void remove_user(String id) throws UserNotFoundException{
        if(!user_repository.exists(id)){
            throw new UserNotFoundException("User with id "+id+" not found");
        }

        User user = user_repository.findByID(id);
        user_repository.remove(user.getID());
        auth_system.remove_user(user);
    }

    public User find_by_username(String username) throws UserNotFoundException{
        return user_repository.getAll().stream()
            .filter(u -> u.getName().equalsIgnoreCase(username))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("User with username "+username+" not found"));
    }

    public List<User> find_by_role(Class <? extends Profile> role_type) throws InvalidProfileException{
        List<User> result = user_repository.getAll().stream()
        .filter(u -> u.getProfiles().stream().anyMatch(role_type::isInstance))
        .collect(Collectors.toList());
        
        if(result.isEmpty()){
            throw new InvalidProfileException("Profile with role type"+role_type+"does not exist");
        }

        return result;
    }
    
    public List<User> getAllUsers(){
        return user_repository.getAll();
    }
}
