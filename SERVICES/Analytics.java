package SERVICES;

import CONTENT.Content;
import CONTENT.Genre;
import USER.Profile;
//import USER.AdminProfile;
import EXCEPTIONS.*;

import java.util.*;
import java.util.stream.Collectors;


public class Analytics {
    private final HistoryTracker history_tracker;
    private final ContentRepository content_repository;
    
    public Analytics(HistoryTracker history_tracker, ContentRepository content_repository){
        this.history_tracker = history_tracker;
        this.content_repository = content_repository;
    }

    /* Profile level Analytics */
    
    public Genre get_favorite_genre(Profile profile) throws InvalidProfileException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        List<HistoryTracker.HistoryEntry> history_entries = history_tracker.getHistory(profile);
        if(history_entries.isEmpty()){
            return null;
        }

        Map<Genre, Long> genre_count = history_entries.stream().map(entry ->
            {
                try{
                    return content_repository.findByID(entry.get_content_id());
                }catch(ContentNotFoundException e){
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .map(Content::getGenre)
            .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));
        
        return genre_count.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public String generateReport(Profile profile) throws InvalidProfileException {
        List<HistoryTracker.HistoryEntry> history = history_tracker.getHistory(profile);
        Genre favorite_genre = get_favorite_genre(profile);

        return "Analytics Report for Profile: " + profile.getID() + "\n"
            + "Total content watched: " + history.size() + "\n"
            + "Favorite Genre: " + (favorite_genre == null ? "N/A" : favorite_genre.name()) + "\n";
    }

    /* System-wide analytics */

    private Content get_most_watched_content(){
        Map<String,Long> count_by_id = history_tracker.getAllHistory().stream()
            .collect(Collectors.groupingBy(HistoryTracker.HistoryEntry::get_content_id, Collectors.counting()));
        
        return count_by_id.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> {
                try{
                    return content_repository.findByID(entry.getKey());
                }catch(ContentNotFoundException e){
                    return null;
                }
            }).orElse(null);
    }

    public List<Content> get_most_watched_contents(int limit) {
        Map<String, Long> count_by_id = history_tracker.getAllHistory().stream()
            .collect(Collectors.groupingBy(HistoryTracker.HistoryEntry::get_content_id, Collectors.counting()));

        return count_by_id.entrySet().stream()
            .map(entry -> {
                try {
                    return content_repository.findByID(entry.getKey());
                } catch (ContentNotFoundException e) {
                    return null;
                }
            })
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
        .entrySet().stream()
        .sorted(Map.Entry.<Content, Long>comparingByValue().reversed())
        .limit(limit)
        .map(Map.Entry::getKey)
        .toList();
    }

    private Genre getMostPopularGenre() {
        Map<Genre, Long> genreCount = history_tracker.getAllHistory().stream()
            .map(entry -> {
                try {
                    return content_repository.findByID(entry.get_content_id());
                } catch (ContentNotFoundException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .map(Content::getGenre)
            .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        return genreCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public String generateReport() {
        Content topContent = get_most_watched_content();
        Genre topGenre = getMostPopularGenre();

        return "System-wide Analytics Report\n"
            + "Most Watched Content: " + (topContent == null ? "N/A" : topContent.getTitle()) + "\n"
            + "Most Popular Genre: " + (topGenre == null ? "N/A" : topGenre.name()) + "\n";
    }
}
