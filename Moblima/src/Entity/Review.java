package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Review implements Serializable {
    private final Date date;
    private final int rating;
    private final String review;
    private final Movie movie;
    private final String name;

    public Review(int rating, String review, Movie movie, String name) {
        if (rating>5) this.rating=5;
        else if (rating<1) this.rating=1;
        else this.rating= rating;

        this.date= new Date();
        this.review = review;
        this.movie = movie;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review1)) return false;
        return Objects.equals(getReview(), review1.getReview()) && Objects.equals(getMovie(), review1.getMovie()) && Objects.equals(getName(), review1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReview(), getMovie(), getName());
    }
}
