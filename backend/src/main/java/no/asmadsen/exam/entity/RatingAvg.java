package no.asmadsen.exam.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.UUID;

public class RatingAvg {
    @Column(name = "movie_id")
    private UUID movieId;
    @Column(name = "rating")
    private Double rating;

    public RatingAvg() {}

    public RatingAvg(UUID movieId, Double rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public UUID getMovieId() { return movieId; }

    public void setMovieId(UUID movieId) { this.movieId = movieId; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) { this.rating = rating; }
}
