package USER;
import java.util.List;
import CONTENT.Genre;
import EXCEPTIONS.InvalidProfileException;

public class ParentProfile implements Profile{
    private String username;
    private String id;
    private List<Genre> preferences;

    public ParentProfile(String username, String id, List<Genre> preferences) throws InvalidProfileException{
        if(username == null || username.trim().isEmpty()){
            throw new InvalidProfileException("Parent profile must have a username");
        }
        
        if(preferences == null || preferences.isEmpty()){
            throw new InvalidProfileException("Parent profile must contain at least one preference");
        }

        this.username = username;
        this.id = id;
        this.preferences = preferences;
    }

    @Override
    /* Returns username */
    public String getUsername(){
        return username;
    }

    @Override
    public String getID(){
        return id;
    }

    @Override
    /* Returns preferences */
    public List<Genre> getPreferences(){
        return preferences;
    }
}
