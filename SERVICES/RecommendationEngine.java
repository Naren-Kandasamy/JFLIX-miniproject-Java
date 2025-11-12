package SERVICES;

import USER.Profile;
import CONTENT.Content;
import CONTENT.Genre;
import EXCEPTIONS.*;
import java.util.*;
import CONTENT.Series;
import CONTENT.Episode;

public class RecommendationEngine {

    private final HistoryTracker historyTracker;
    private final ContentRepository contentRepository;
    private final Analytics analytics;

    public RecommendationEngine(HistoryTracker historyTracker,
                                ContentRepository contentRepository,
                                Analytics analytics) {
        this.historyTracker = historyTracker;
        this.contentRepository = contentRepository;
        this.analytics = analytics;
    }

    // Hybrid Recommendation
    public List<Content> recommend(Profile profile, int limit) throws InvalidProfileException, ContentNotFoundException {
        if (profile == null) {
            throw new InvalidProfileException("Profile cannot be null");
        }

        List<Content> recommendations = new ArrayList<>();
        Set<String> alreadyWatchedIds = new HashSet<>();
        
        List<HistoryTracker.HistoryEntry> history = historyTracker.getHistory(profile);
        for (HistoryTracker.HistoryEntry entry : history) {
            alreadyWatchedIds.add(entry.get_content_id());
        }

        Genre favoriteGenre = analytics.get_favorite_genre(profile);

        if (favoriteGenre != null) {
            List<Content> genreBased = contentRepository.getAll().stream()
                    .filter(c -> !alreadyWatchedIds.contains(c.getID())) // exclude watched
                    .filter(c -> c.getGenre() == favoriteGenre)           // match favorite genre
                    .limit(limit)
                    .toList();

            recommendations.addAll(genreBased);
        }

        if (recommendations.size() < limit) {
            List<Content> trending = analytics.get_most_watched_contents(limit);

            for (Content c : trending) {
                if (recommendations.size() >= limit) break;
                if (!alreadyWatchedIds.contains(c.getID()) && !recommendations.contains(c)) {
                    recommendations.add(c);
                }
            }
        }
        return recommendations;
    }
}