package no.asmadsen.exam.controller;

import no.asmadsen.exam.entity.Rating;
import no.asmadsen.exam.entity.RatingId;
import no.asmadsen.exam.entity.User;
import no.asmadsen.exam.repository.RatingRepository;
import no.asmadsen.exam.service.MovieService;
import no.asmadsen.exam.service.RatingService;
import no.asmadsen.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Named
@RequestScoped
public class RatingController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    private String movieId;

    private String score;

    private String review;

    private boolean editing;

    public String rateMovie() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                                                                   .getAuthentication()
                                                                   .getPrincipal();
        User user = userService.findByEmail(principal.getUsername());

        movieService.rateMovie(user.getId(), UUID.fromString(movieId), Integer.parseInt(score), review);

        return "/movie.jsf?faces-redirect=true&includeViewParams=true&editing=false";
    }

    public List<Rating> findAllRatings(UUID movieId) {
        return ratingService.findByMovie(movieId);
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int ratingsByUser(UUID userId) {
        return ratingService.countReviewsByUser(userId);
    }

    public boolean isRatedByUser(UUID userId, UUID movieId) {
        return ratingRepository.existsById(new RatingId(userId, movieId));
    }

    public void initEditing(UUID userID) {
        if (editing && movieId != null && userID != null) {
            UUID movieId = UUID.fromString(this.movieId);

            ratingRepository.findById(new RatingId(userID, movieId))
                            .ifPresent(rating -> {
                setScore(String.valueOf(rating.getRating()));
                setReview(rating.getReview());
            });
        }
    }
}
