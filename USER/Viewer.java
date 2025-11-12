package USER;
import CONTENT.Content;
import EXCEPTIONS.ContentNotFoundException;
import EXCEPTIONS.InvalidProfileException;
import EXCEPTIONS.InvalidRatingException;
import java.util.*;
import SERVICES.RecommendationEngine;
import SERVICES.HistoryTracker;
import EXCEPTIONS.AuthorizationException;
import CONTENT.Series;
import CONTENT.Episode;

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

    public void viewContent(Content content, HistoryTracker historyTracker) throws ContentNotFoundException, AuthorizationException, InvalidProfileException {
        if (content == null) {
            throw new ContentNotFoundException("Content must be selected to view it");
        }

        if (profile instanceof KidProfile && content.getAgeRating() > 13) {
            throw new AuthorizationException("You are not authorized to view this content.");
        }

        if (content instanceof Series) {
            Series series = (Series) content;
            List<Episode> episodes = series.getEpisodes();

            if (episodes.isEmpty()) {
                System.out.println("This series has no episodes.");
                return;
            }

            System.out.println("Episodes for " + series.getTitle() + ":");
            for (int i = 0; i < episodes.size(); i++) {
                System.out.println((i + 1) + ". " + episodes.get(i).getEpisodeTitle());
            }

            System.out.print("Select an episode to watch: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice > 0 && choice <= episodes.size()) {
                Episode selectedEpisode = episodes.get(choice - 1);
                System.out.println("Now viewing episode: " + selectedEpisode.getEpisodeTitle());
                historyTracker.add_to_history(profile, content, selectedEpisode.getEpisodeId());
            } else {
                System.out.println("Invalid episode selection.");
            }
        } else {
            System.out.println("Now viewing : " + content.getTitle());
            System.out.println("Description: " + content.getDescription());
            historyTracker.add_to_history(profile, content);
        }
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
