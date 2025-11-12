package SERVICES;

import USER.Profile;
import CONTENT.Content;
import EXCEPTIONS.*;

import java.util.*;
import java.time.*;

public class HistoryTracker {

    public static class HistoryEntry{
        private final String content_id;
        private final String content_title;
        private final LocalDateTime watched_at;

        public HistoryEntry(Content content, LocalDateTime watched_at){
            this.content_id = content.getID();
            this.content_title = content.getTitle();
            this.watched_at = watched_at;
        }

        public String get_content_id(){
            return content_id;
        }

        public String get_content_title(){
            return content_title;
        }

        public LocalDateTime get_watched_at(){
            return watched_at;
        }

        public Content get_content(ContentRepository content_Repository) throws ContentNotFoundException{
            return content_Repository.findByID(content_id);
        }
        
        @Override
        public String toString(){
            return (content_title + " (id - " +content_id + ") watched at=" + watched_at);
        }
    }

    private final Map<String, List<HistoryEntry>> history_map = new HashMap<>();
    private final Map<String, Map<String, Set<String>>> series_history_map = new HashMap<>();

    public void add_to_history(Profile profile, Content content) throws ContentNotFoundException, InvalidProfileException{
        add_to_history(profile, content, null);
    }

    public void add_to_history(Profile profile, Content content, String episodeId) throws ContentNotFoundException, InvalidProfileException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        if(content == null){
            throw new ContentNotFoundException("Content cannot be null");
        }

        String pid = profile.getID();
        history_map.computeIfAbsent(pid,k -> new ArrayList<>()).add(new HistoryEntry(content,LocalDateTime.now())); 

        if (episodeId != null) {
            series_history_map.computeIfAbsent(pid, k -> new HashMap<>())
                .computeIfAbsent(content.getID(), k -> new HashSet<>()).add(episodeId);
        }
    }

    public List<HistoryEntry> getHistory(Profile profile) throws InvalidProfileException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        return Collections.unmodifiableList(history_map.getOrDefault(profile.getID(), Collections.emptyList()));
    }

    public List<HistoryEntry> getHistory(String pid) throws InvalidProfileException{
        if(pid == null){
            throw new InvalidProfileException("Invalid profile id");
        }

        return Collections.unmodifiableList(history_map.getOrDefault(pid, Collections.emptyList()));
    }

    public List<HistoryEntry> getAllHistory() {
        return history_map.values().stream()
                .flatMap(List::stream)
                .toList(); // requires Java 16+, else use collect(Collectors.toList())
    }

    public Set<String> getWatchedEpisodes(Profile profile, String seriesId) {
        if (profile == null || seriesId == null) {
            return Collections.emptySet();
        }
        return series_history_map.getOrDefault(profile.getID(), Collections.emptyMap())
            .getOrDefault(seriesId, Collections.emptySet());
    }


    public void clear_history(Profile profile) throws InvalidProfileException{
        if(profile == null){
            throw new InvalidProfileException("Profile cannot be null");
        }

        history_map.remove(profile.getID());
    }
}