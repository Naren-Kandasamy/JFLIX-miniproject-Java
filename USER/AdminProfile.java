package USER;
import java.util.List;
import java.util.ArrayList;
import CONTENT.Genre;
import CONTENT.Content;
import EXCEPTIONS.ContentNotFoundException;
import java.io.*;

public class AdminProfile implements Profile{
    private String username;
    private List<Genre> preferences;
    private List<Content> managedContent;

    public AdminProfile(String username, List<Genre> preferences){
        this.username = username;
        this.preferences = preferences;
        this.managedContent = new ArrayList<>();
    }

    @Override
    /* Returns username */
    public String getUsername(){
        return username;
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

    /* Updating the content */
    public void updateContent(String oldTitle) throws IOException, ContentNotFoundException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean found = false; // flag for content

        for(Content c : managedContent){
            if(c.getTitle().equalsIgnoreCase(oldTitle)){
                found = true;

                boolean updating = true;
                while(updating){
                    System.out.println("\nCurrent Content:");
                    System.out.println("1. Title: " + c.getTitle());
                    System.out.println("2. Description: " + c.getDescription());
                    System.out.println("3. Duration: " + c.getDuration() + " mins");
                    System.out.println("4. Director: " + c.getDirector());
                    System.out.println("5. Done");

                    System.out.print("\nChoose option [1-5]: "); int choice = Integer.parseInt(br.readLine());

                    switch(choice){
                        case 1:
                            {
                                System.out.print("Enter new title: ");
                                String newTitle = br.readLine();
                                c.setTitle(newTitle);
                                System.out.println("Title updated successfully!");
                                break;
                            }
                        case 2:
                            {
                                System.out.print("Enter new description: ");
                                String newDesc = br.readLine();
                                c.setDescription(newDesc);
                                System.out.println("Description updated successfully!");
                                break;
                            }
                        case 3:
                            {
                                System.out.print("Enter new duration (minutes): ");
                                int newDur = Integer.parseInt(br.readLine());
                                br.readLine();
                                c.setDuration(newDur);
                                System.out.println("Duration updated successfully!");
                                break;
                            }
                        case 4:
                            {
                                System.out.print("Enter new director: ");
                                String newDir = br.readLine();
                                c.setDirector(newDir);
                                System.out.println("Director updated successfully!");
                                break;
                            }

                        case 5:
                            {
                                updating = false;
                                System.out.println("Update process finished.");
                                break;
                            }
                        default:
                            System.out.println("Invalid choice, try again.");
                    }
                }
                break;
            }
        }
        if(!found){
            throw new ContentNotFoundException("Content with title "+oldTitle+" not found");
        }
    }
}
