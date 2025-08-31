package CONTENT;
import java.util.*;

public class Series extends Content{
    private int number_of_seasons;
    private int total_episodes;
    private List<Episode> episodes;

    public Series(String id, String title, Genre genre, int duration, String director, int number_of_seasons,int total_episodes){
        super(id,title,genre,duration,director);
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

    public void addEpisode(Episode e) {
        episodes.add(e);
        total_episodes = episodes.size();
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}


