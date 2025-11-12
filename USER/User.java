package USER;
import CORE.Identifiable; 
import EXCEPTIONS.DuplicateUserException;
import EXCEPTIONS.InvalidProfileException;

import java.io.Serializable;
import java.util.*;

public class User implements Identifiable, Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private List<Profile> profiles;
    private transient Profile activeProfile; // Active profile should not be serialized

    public User() {
        this.id = "default_id";
        this.username = "default_user";
        this.profiles = new ArrayList<>();
    }

    public User(String id, String username) throws InvalidProfileException{
        if(id == null || id.trim().isEmpty()){
            throw new InvalidProfileException("User ID cannot be null or empty");
        }

        if(username == null || username.trim().isEmpty()){
            throw new InvalidProfileException("Username cannot be null or empty");
        }

        this.id = id;
        this.username = username;
        this.profiles = new ArrayList<>();
    }

    public void addProfile(Profile profile) throws InvalidProfileException, DuplicateUserException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        for (Profile p : profiles) {
            if (p.getUsername().equals(profile.getUsername())) {
                throw new DuplicateUserException("Profile with username '" + profile.getUsername() + "' already exists for user: " + id);
            }
        }

        profiles.add(profile);
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return username;
    }


    public List<Profile> getProfiles(){
        return profiles;
    }

    public void selectProfile(String username) throws InvalidProfileException{
        for(Profile p : profiles){
            if(p.getUsername().equals(username)){
                this.activeProfile = p;
                return;
            }
        }
        throw new InvalidProfileException("Profile '" + username + "' not found for user " + id);
    }

    public Profile getActiveProfile() throws InvalidProfileException{
        if(activeProfile == null){
            if (!profiles.isEmpty()) {
                activeProfile = profiles.get(0); // Default to the first profile if none is selected
            } else {
                throw new InvalidProfileException("No profiles available for user " + id);
            }
        }
        return activeProfile;
    }
}
