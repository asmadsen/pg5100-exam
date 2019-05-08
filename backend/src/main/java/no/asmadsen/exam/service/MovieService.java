package no.asmadsen.exam.service;

import no.asmadsen.exam.entity.Movie;
import no.asmadsen.exam.entity.Rating;
import no.asmadsen.exam.entity.RatingAvg;
import no.asmadsen.exam.entity.RatingId;
import no.asmadsen.exam.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class MovieService {
    @Autowired
    private EntityManager em;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> computeRating(Iterable<Movie> input) {
        Observable<RatingAvg> ratings = Observable.from(input)
                                                  .map(Movie::getId)
                                                  .toList()
                                                  .flatMap(ids -> {
                                                      return Observable.from(em.createNamedQuery(
                                                              Rating.AVG_RATING_BY_MOVIES,
                                                              RatingAvg.class)
                                                                               .setParameter(
                                                                                       "movieIds",
                                                                                       ids)
                                                                               .getResultList());
                                                  });
        return Observable.from(input).map(movie -> {
            RatingAvg avg = ratings.toBlocking()
                                   .firstOrDefault(null, rating -> rating.getMovieId().equals(movie.getId()));
            if (avg != null) {
                movie.setAvgRating(avg.getRating());
            }
            return movie;
        }).toList().toBlocking().first();
    }

    public Movie computeRating(Movie movie) {
        try {
            RatingAvg ratingAvg = em.createNamedQuery(
                    Rating.AVG_RATING_BY_MOVIES,
                    RatingAvg.class)
                                    .setParameter(
                                            "movieIds",
                                            Collections.singletonList(movie.getId()))
                                    .getSingleResult();

            movie.setAvgRating(ratingAvg.getRating());
        } catch (Exception ignored) {
        }

        return movie;
    }

    public List<Movie> findAll() {
        List<Movie> movies = computeRating(movieRepository.findAll());
        movies.sort((a, b) -> {
            if (a.getAvgRating() == b.getAvgRating()) return 0;
            return a.getAvgRating() < b.getAvgRating() ? 1 : -1;
        });
        return movies;
    }

    public void rateMovie(UUID userId, UUID movieId, int score, String review) {
        RatingId ratingId = new RatingId(userId, movieId);
        Rating rating = em.find(Rating.class, ratingId);
        boolean exists = rating != null;
        if (rating == null) {
            rating = new Rating();
        }
        rating.setId(ratingId);
        rating.setRating(score);
        rating.setReview(review);

        if (!exists) {
            em.persist(rating);
        }
    }

    public List<Rating> findAllRatings(UUID movieId) {
        return em.createNamedQuery(Rating.FIND_ALL_BY_MOVIE_ID, Rating.class)
                 .setParameter("movieId", movieId)
                 .getResultList();
    }

    public UUID createMovie(String title, String image, String plot, String released, int runtime, Set<String> genres, String director) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setImage(image);
            movie.setPlot(plot);
            movie.setReleased(format.parse(released));
            movie.setRuntime(runtime);
            movie.setGenres(genres);
            movie.setDirector(director);
            em.persist(movie);

            return movie.getId();
        } catch (ParseException ignored) {
        }

        return null;
    }

    public Set<String> findAllGenres() {
        return new HashSet<String>(em.createNamedQuery(Movie.FIND_ALL_GENRES).getResultList());
    }
}
