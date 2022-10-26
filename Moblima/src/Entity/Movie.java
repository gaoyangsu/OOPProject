package Entity;
import Entity.MovieEnums.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static Boundary.Staff.AddScreeningSchedule.dateFormat;
import static Controller.MiscMethods.*;


public class Movie implements java.io.Serializable {

    private int movieId;
    private String movieName;
    private String movieDesc;
    private String movieDirector;
    private ArrayList<String> movieCast;

    private MovieStatus movieStatus;
    private AgeAdvisory ageAdvisory;

    private Date releaseDate;
    private Date takeDownDate; //takeDownDate is to be used to automatically change to END_OF_SHOWING AFTER THE DATE IS OVER;

    private int salesNum;

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
    public void setReleaseDate(){
        this.releaseDate=dateInput();
    }

    public Date getReleaseDate(){
        return this.releaseDate;
    }

    public String releaseDateToString(){
        return dateOutput(this.releaseDate);
    }

    public void setTakeDownDate() {
        this.takeDownDate=dateInput();
    }

    public Date getTakeDownDate(){
       return this.takeDownDate;
    }

    public String takeDownDateToString(){
        return dateOutput(this.takeDownDate);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public ArrayList<String> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(ArrayList<String> movieCast) {
        this.movieCast = movieCast;
    }

    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    public AgeAdvisory getAgeAdvisory() {
        return ageAdvisory;
    }

    public void setAgeAdvisory(AgeAdvisory ageAdvisory) {
        this.ageAdvisory = ageAdvisory;
    }

    public int getSalesNum() {
        return salesNum;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return getMovieName().equals(movie.getMovieName()) && getMovieDesc().equals(movie.getMovieDesc()) && getMovieDirector().equals(movie.getMovieDirector());
    }


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