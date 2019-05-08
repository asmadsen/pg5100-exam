package no.asmadsen.exam.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Rating.AVG_RATING_BY_MOVIES, query = "select new no.asmadsen.exam.entity.RatingAvg(r.id.movieId, avg(r.rating)) from Rating r where r.id.movieId in :movieIds group by r.id.movieId"),
        @NamedQuery(name = Rating.FIND_ALL_BY_MOVIE_ID, query = "select r from Rating r where r.id.movieId = :movieId"),
        @NamedQuery(name = Rating.FIND_ALL_BY_USER_ID, query = "select r from Rating r where r.id.userId = :userId"),
})
@Entity
@Table(name = "ratings")
public class Rating {
    public static final String AVG_RATING_BY_MOVIES = "AVG_RATING_BY_MOVIES";
    public static final String FIND_ALL_BY_MOVIE_ID = "FIND_ALL_BY_MOVIE_ID";
    public static final String FIND_ALL_BY_USER_ID = "FIND_ALL_BY_USER_ID";

    @EmbeddedId
    private RatingId id;

    @NotNull
    @Range(min = 1, max = 5)
    private int rating;

    private String review;

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
