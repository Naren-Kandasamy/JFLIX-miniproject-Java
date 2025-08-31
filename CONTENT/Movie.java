package CONTENT;

import java.util.Calendar;

public class Movie extends Content{
    private int release_year;

    public Movie(String id, String title, Genre genre, int duration, String director, int release_year){
        super(id,title,genre,duration,director);
        this.release_year = release_year;
    }

    public int getReleaseYear(){
        return release_year;
    }

    public boolean isClassic(){
        return release_year < 2000;
    }

    public boolean isRecentRelease(){
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        return release_year >= current_year - 2;
    }
}
