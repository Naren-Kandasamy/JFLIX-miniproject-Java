package CONTENT;
import java.io.Serializable;
import java.util.*;

public class Episode implements Serializable{
    private static final long serialVersionUID = 1L;
    private String episodeId;
    private String episodeTitle;
    private int episodeNumber;
    private Date releaseDate;
    private int duration;

    public Episode(String episodeId, String episodeTitle, int episodeNumber, Date releaseDate, int duration) {
        this.episodeId = episodeId;
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getEpisodeId() {
        return episodeId;
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