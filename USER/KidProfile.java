package USER;

import java.util.List;
import EXCEPTIONS.InvalidProfileException;
import CONTENT.Genre;

public class KidProfile implements Profile{
    private String username;
    private List<Genre> preferences;
    
    public KidProfile(String username, List<Genre> preferences) throws InvalidProfileException{
        if(username == null || username.trim().isEmpty()){
            throw new InvalidProfileException("Kid profile must have a username");
        }
        if(preferences == null || preferences.isEmpty()){
            throw new InvalidProfileException("Kid profile must have atleast 1 preference");
        }
        
        this.username = username;
        this.preferences = preferences;
    }

    @Override
    /* Gives the profile's username */
    public String getUsername() {
        return username;
    }

    @Override
    /* Gives the profile's preferences */
    public List<Genre> getPreferences(){ 
        return preferences;
    }
}
