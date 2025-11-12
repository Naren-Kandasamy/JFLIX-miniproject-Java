package CONTENT;
import java.io.Serializable;
import java.util.*;

public class Series extends Content implements Serializable{
    private static final long serialVersionUID = 1L;
    private int number_of_seasons;
    private int total_episodes;
    private List<Episode> episodes;

    public Series(String id, String title, Genre genre, int duration, String director, int number_of_seasons,int total_episodes, int ageRating){
        super(id,title,genre,duration,director, ageRating);
        this.number_of_seasons = number_of_seasons;
        this.total_episodes = total_episodes;
        this.episodes = new ArrayList<>();
    }
    
    public int getNumberOfSeasons(){
        return number_of_seasons;
    }

    public int getTotalEpisodes(){
        return total_episodes;
    }

    public void addEpisode(String episodeTitle, int episodeNumber, Date releaseDate, int duration) {
        String episodeId = this.id + "_e_" + episodeNumber;
        Episode newEpisode = new Episode(episodeId, episodeTitle, episodeNumber, releaseDate, duration);
        episodes.add(newEpisode);
        total_episodes = episodes.size();
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public String getContentType() {
        return "Series";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Seasons: " + number_of_seasons;
    }
}
