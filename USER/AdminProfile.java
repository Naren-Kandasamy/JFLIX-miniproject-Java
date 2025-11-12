package USER;
import java.util.List;
import java.util.ArrayList;
import CONTENT.Genre;
import CONTENT.Content;
import EXCEPTIONS.ContentNotFoundException;
import java.io.*;

public class AdminProfile implements Profile, Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String id;
    private List<Genre> preferences;
    private List<Content> managedContent;

    public AdminProfile(String username, List<Genre> preferences, String id){
        this.username = username;
        this.id = id;
        this.preferences = preferences;
        this.managedContent = new ArrayList<>();
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

    /* ADMIN FUNCTIONALITIES */

    /* Adding content */
    public void addContent(Content content, String description){
        if(content != null && !managedContent.contains(content)){
            managedContent.add(content);
            content.setDescription(description);
            System.out.println("Content Added: "+content.getTitle());
        }
    }

    /* Removing the content */
    public void removeContent(String title) throws ContentNotFoundException{
        boolean removed = managedContent.removeIf(c -> c.getTitle().equalsIgnoreCase(title));
        if(removed == false)
            throw new ContentNotFoundException("Content with title '"+title+"' not found");
    }


}
