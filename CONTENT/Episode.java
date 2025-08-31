package CONTENT;
import java.util.*;

public class Episode {
    private String episodeTitle;
    private int episodeNumber;
    private Date releaseDate;
    private int duration;

    public Episode(String episodeTitle, int episodeNumber, Date releaseDate, int duration) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getDuration() {
        return duration;
    }
}