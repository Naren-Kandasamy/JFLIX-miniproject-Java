package CONTENT;

import java.io.Serializable;
import java.util.Calendar;

public class Movie extends Content implements Serializable{
    private static final long serialVersionUID = 1L;
    private int release_year;

    public Movie(String id, String title, Genre genre, int duration, String director, int release_year, int ageRating){
        super(id,title,genre,duration,director, ageRating);
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

    @Override
    public String getContentType() {
        return "Movie";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Release Year: " + release_year;
    }
}
