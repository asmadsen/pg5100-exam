package no.asmadsen.exam.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.PipedReader;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class RatingId implements Serializable {
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "movie_id")
    private UUID movieId;

    public RatingId() {}

    public RatingId(UUID userId, UUID movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getMovieId() {
        return movieId;
    }
}
