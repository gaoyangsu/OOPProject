package Entity;
import Entity.MovieEnums.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static Boundary.Staff.AddScreeningSchedule.dateFormat;
import static Controller.MiscMethods.*;

/**
    Entity Class representing a Movie
    A particular Movie Class contains all of the details about a movie,
    the enums of movieStatus and ageAdvisory,
    as well as take down and release Dates(for computation of End of Show, Preview, Coming Soon Status)
    @version 1.0
    @since 2022-10-25
 */
public class Movie implements java.io.Serializable {
	
    /**
     * the {@code long} UID for Movie Serialiser
     */
	private static final long serialVersionUID=1906756998803084247L;

    /**
     * the {@code int} movieID chosen for this Movie
     */
    private int movieId;

    /** Name of movie */
    private String movieName;

    /** Sypnopsis of movie */
    private String movieDesc;

    /** Director of movie */
    private String movieDirector;

    /** An ArrayList of movieCast for the movie */
    private ArrayList<String> movieCast;

    /** enum movieStatus */
    private MovieStatus movieStatus;

    /** enum ageAdvisory G PG PG13 etc */
    private AgeAdvisory ageAdvisory;

    /** Date class releaseDate */
    private Date releaseDate;

    /** Date class takeDownDate; takeDownDate is to be used to automatically change to END_OF_SHOWING AFTER THE DATE IS OVER;
 */
    private Date takeDownDate; 

    /** int number of sales for this particular movie */

    private int salesNum;


    /** Movie Constructor with
	 * @param movieName
	 * @param movieDesc
	 * @param movieDesc
     * @param movieDirector
     * @param movieCast
     * @param movieStatus
     * @param ageAdvisory
	 */
    public Movie(int movieId, String movieName, String movieDesc, String movieDirector, ArrayList<String> movieCast, MovieStatus movieStatus, AgeAdvisory ageAdvisory) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieStatus = movieStatus;
        this.ageAdvisory = ageAdvisory;
        this.salesNum = 0;
    }
    
    
    /** 
     * method to increase sales count of movie each time when 
     * movieGoer buys a ticket
     * @param count
     */
    public void increaseCount(int count) {
    	this.salesNum+=count;
    }
    

    /** 
     * method to set release date of movie
     */
    public void setReleaseDate(){
        this.releaseDate=dateInput();
    }

    
    /** 
     * method to get release date of movie
     * @return Date
     */
    public Date getReleaseDate(){
        return this.releaseDate;
    }

    
    /** 
     * method to convert the releaseDate to String
     * @return String
     */
    public String releaseDateToString(){
        return dateOutput(this.releaseDate);
    }

    /** 
     * method to set takeDown date of movie
     */
    public void setTakeDownDate() {
        this.takeDownDate=dateInput();
    }

    
    /** 
     * method to get takedown date of movie
     * @return Date
     */
    public Date getTakeDownDate(){
       return this.takeDownDate;
    }

    
    /** 
     * method to convert the takeDownDate to String
     * @return String
     */
    public String takeDownDateToString(){
        return dateOutput(this.takeDownDate);
    }

    
    /** 
     * method to get movie ID
     * @return int
     */
    public int getMovieId() {
        return movieId;
    }

    
    /** 
     * method to set movie ID
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    
    /** 
     * method to get the movie name
     * @return String
     */
    public String getMovieName() {
        return movieName;
    }

    
    /** 
     * method to set the movie name
     * @param movieName
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    
    /** 
     * method to get the sypnopsis of the movie
     * @return String
     */
    public String getMovieDesc() {
        return movieDesc;
    }

    
    /** 
     * method to set the sypnopsis of the movie
     * @param movieDesc
     */
    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    
    /** 
     * method to get the director of the movie
     * @return String
     */
    public String getMovieDirector() {
        return movieDirector;
    }

    
    /** 
     *  method to set the director of the movie
     * @param movieDirector
     */
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    
    /** 
     * method to get the movie casting
     * @return ArrayList<String>
     */
    public ArrayList<String> getMovieCast() {
        return movieCast;
    }

    
    /** 
     * method to set the movie casting
     * @param movieCast
     */
    public void setMovieCast(ArrayList<String> movieCast) {
        this.movieCast = movieCast;
    }

    
    /** 
     * method to get the movieStatus
     * @return MovieStatus
     */
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    
    /** 
     * method to set the movieStatus
     * @param movieStatus
     */
    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    
    /** 
     * method to get the AgeAdvisory
     * @return AgeAdvisory
     */
    public AgeAdvisory getAgeAdvisory() {
        return ageAdvisory;
    }

    
    /** 
     * method to set the AgeAdvisory
     * @param ageAdvisory
     */
    public void setAgeAdvisory(AgeAdvisory ageAdvisory) {
        this.ageAdvisory = ageAdvisory;
    }

    
    /** 
     * method to get the current Sales Number of the movie
     * @return int
     */
    public int getSalesNum() {
        return this.salesNum;
    }


    
    /** 
     * method to get the movie object based on the equals method
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return getMovieName().equals(movie.getMovieName()) && getMovieDesc().equals(movie.getMovieDesc()) && getMovieDirector().equals(movie.getMovieDirector());
    }

    
    /** 
     * hash the movie object based on movie id and movie name
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getMovieName());
    }

    
    /** 
     * method to return the movie details(above mentioned) to a format
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder casts = new StringBuilder();
        stringBuilder.append("Movie ID  :").append(getMovieId()).append("\n");
        stringBuilder.append("Movie Name:").append(getMovieName()).append("\n");
        stringBuilder.append("Restrict:  ").append(getAgeAdvisory().toString()).append("\n");
        stringBuilder.append("Director:  ").append(getMovieDirector()).append("\n");
        stringBuilder.append("Synopsis:  ").append("\"").append(getMovieDesc()).append("\"").append("\n");

        stringBuilder.append("Casts:     ");
        for (String s : movieCast) casts.append(s).append(", ");
        stringBuilder.append(addLinebreaks(casts.toString(), 50, 10));
        stringBuilder.append("\n");
        //stringBuilder.append("Rating:   ");
        //if (rating == 0.0) stringBuilder.append("No rating").append("\n");
        //else stringBuilder.append(getMovieRating(this)).append("/5.0").append("\n");
        stringBuilder.append("Status:    ").append(movieStatus.toString()).append("\n");

        return stringBuilder.toString();
    }
}