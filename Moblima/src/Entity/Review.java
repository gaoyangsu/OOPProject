package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
    Entity Class representing a Review
    the reviews must be tagged to a particular movie
    @version 1.0
    @since 2022-10-25
 */
public class Review implements Serializable {
    
    /**
     * the {@code Date} of the review/comment made
     */
    private final Date date;
    /**
     * the {@code int} of a single review ranging from 1-5
     */
    private final int rating;
    /**
     * the {@code String} of a comment of the movie
     */
    private final String review;
    /**
     * the {@code Movie} of which movie is the comment/rating referring to
     */
    private final Movie movie;
    /**
     * the {@code String} of the name of the commenter
     */
    private final String name;

    /**
     * Constuctor of a particular review
     * Need to include @param rating 
     * @param review
     * @param movie
     * @param name
     */
    public Review(int rating, String review, Movie movie, String name) {
        if (rating>5) this.rating=5;
        else if (rating<1) this.rating=1;
        else this.rating= rating;

        this.date= new Date();
        this.review = review;
        this.movie = movie;
        this.name = name;
    }

    
    /** 
     * getter method of the date of the comment
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    
    /** 
     * getter method of an individual rating
     * @return int
     */
    public int getRating() {
        return rating;
    }

    
    /** 
     * getter method of the String of Review
     * @return String
     */
    public String getReview() {
        return review;
    }

    
    /** 
     * getter method of a Single movie
     * @return Movie
     */
    public Movie getMovie() {
        return movie;
    }

    
    /** 
     * getter method of the name of the commentor
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * equals method to find the particular object of review
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review1)) return false;
        return Objects.equals(getReview(), review1.getReview()) && Objects.equals(getMovie(), review1.getMovie()) && Objects.equals(getName(), review1.getName());
    }

    
    /** 
     * method to hash this object via 
     * {@code getReview}, {@code getMovie}, {@code getName}
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getReview(), getMovie(), getName());
    }
}
