package USER;
import CORE.Identifiable; 
import EXCEPTIONS.DuplicateUserException;
import EXCEPTIONS.InvalidProfileException;
import java.util.*;

public class User implements Identifiable{
    private String id;
    private List<Profile> profiles;
    private Profile activeProfile;

    public User(String id) throws InvalidProfileException{
        if(id == null || id.trim().isEmpty()){
            throw new InvalidProfileException("User ID cannot be null or empty");
        }

        this.id = id;
        this.profiles = new ArrayList<>();
    }

    public void addProfile(Profile profile) throws InvalidProfileException, DuplicateUserException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        if(profiles.contains(profile)){
            throw new DuplicateUserException("Profile already exists for user: "+id);
        }

        profiles.add(profile);
    }

    public String getID(){
        return id;
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
            throw new InvalidProfileException("No active profile selected for user");
        }
        return activeProfile;
    }
}
