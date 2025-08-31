package USER;
import CONTENT.Content;
import EXCEPTIONS.ContentNotFoundException;
import EXCEPTIONS.InvalidProfileException;
import EXCEPTIONS.InvalidRatingException;
import java.util.*;

public class Viewer {
    private Profile profile;
    private List<Content> watchList;

    public Viewer(User user, String profileName) throws InvalidProfileException {
    this.profile = user.getProfiles().stream()
        .filter(p -> p.getUsername().equalsIgnoreCase(profileName))
        .findFirst()
        .orElseThrow(() -> new InvalidProfileException("Profile not found: " + profileName));
    this.watchList = new ArrayList<>();
}

    public void viewProfile(){
        System.out.println("Username: "+profile.getUsername());
        System.out.println("Preferences: "+profile.getPreferences());
    }

    public void viewContent(Content content) throws ContentNotFoundException{
        if(content == null){
            throw new ContentNotFoundException("Content must be selected to view it");
        }
        System.out.println("Now viewing : "+content.getTitle());
        System.out.println("Description: "+content.getDescription());
    }

    public void rateContent(Content content, float rating) throws InvalidRatingException{
        if(rating < 1 || rating > 5){
            throw new InvalidRatingException("Rating must be between 1 and 5");
        }
        content.addRating(rating);
        System.out.println("You rated '" + content.getTitle() + "' with " + rating + " stars.");
        System.out.println("Current average rating: " + content.getAverageRating() + " (" + content.getRatingCount() + " ratings)");
    }

    public void addToWatchList(Content content) throws ContentNotFoundException{
        if(content == null){
            throw new ContentNotFoundException("Cannot add null to the watch list");
        }
        if(!watchList.contains(content)){
            watchList.add(content);
            System.out.println("Added to watchlist: " + content.getTitle());
        } else {
            System.out.println("Already present in watchlist");
        }
    }

    public void removeFromWatchlist(String title) throws ContentNotFoundException{
        boolean removed = watchList.removeIf(c -> c.getTitle().equalsIgnoreCase(title));
        if (!removed) {
            System.out.println("Your watch list: ");
            showWatchlist();
            throw new ContentNotFoundException("Content with title '" + title + "' not found in watchlist!");
        }
        System.out.println("Removed from watchlist: " + title);
    }

    public void showWatchlist() {
        if (watchList.isEmpty()) {
            System.out.println("Your watchlist is empty.");
            return;
        }
        System.out.println("Your Watchlist:");
        for (Content c : watchList) {
            System.out.println("- " + c.getTitle());
        }
    }

    public boolean isAdmin() {
        return profile instanceof AdminProfile;
    }

    public AdminProfile asAdmin() {
        if (isAdmin()) {
            return (AdminProfile) profile;
        } else {
            throw new IllegalStateException("This viewer is not an admin!");
        }
    }
}
