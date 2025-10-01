package SERVICES;

import USER.User;
import EXCEPTIONS.UserNotFoundException;

import java.util.*;

public class UserRepository implements Repository<User>{
    private Map<String,User> userMap;
    
    public UserRepository(){
        this.userMap = new HashMap<>();
    }

    @Override
    public void add(User user){
        userMap.put(user.getID(),user); // Adds the User to User mapping 
    }

    @Override   
    public boolean remove(String id) {
        if(!userMap.containsKey(id)){  // or whatever your map is called
            return false;
        }
        userMap.remove(id);
        return true;
    }

    @Override
    public User findByID(String id) throws UserNotFoundException{
        User c = userMap.get(id);
        if(c == null){
            throw new UserNotFoundException();
        }

        return c;
    }

    @Override
    public boolean exists(String id){
        return userMap.containsKey(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }
}
