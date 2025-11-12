package CONTENT;
import CORE.Identifiable;
import java.io.Serializable;

public abstract class Content implements Identifiable, Serializable{
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String title;
    protected Genre genre;
    protected int duration;
    protected String director;
    String description;
    protected float total_rating;
    private int rating_count;
    protected int ageRating;

    // Default constructor to have default genre
    public Content() {
        this("default_id", "No Title", Genre.ACTION, 0, "No Director", 0);
    }

    public Content(String id, String title, Genre genre, int duration, String director, int ageRating){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.total_rating = 0;
        this.rating_count = 0;
        this.ageRating = ageRating;
    }

    public int getAgeRating(){
        return ageRating;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getContentType();

    public String getDetails() {
        return "Title: " + title + ", Genre: " + genre;
    }

    @Override
    public String getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public Genre getGenre(){
        return genre;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int newDuration){
        this.duration = newDuration;
    }

    public String getDirector(){
        return director;
    }

    public void setDirector(String newDirector){
        this.director = newDirector;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setTitle(String title){
        this.title = title;
    }

     public void addRating(float rating) {
        this.total_rating += rating;
        this.rating_count++;
    }

    public float getAverageRating() {
        if (rating_count == 0) return 0;
        return total_rating / rating_count;
    }

    public int getRatingCount(){
        return rating_count;
    }
}
