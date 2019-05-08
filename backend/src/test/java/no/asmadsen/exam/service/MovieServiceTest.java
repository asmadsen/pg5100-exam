package no.asmadsen.exam.service;

import com.github.javafaker.Faker;
import no.asmadsen.exam.StubApplication;
import no.asmadsen.exam.entity.Movie;
import no.asmadsen.exam.entity.Rating;
import no.asmadsen.exam.entity.User;
import no.asmadsen.exam.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest extends ServiceTestBase {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    private Faker faker = new Faker();

    private UUID createMovie() {
        return movieService.createMovie(faker.book().title(), "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                        Collections.singleton("Drama"),
                                        "Quentin Tarantino");
    }

    private User createUser() {
        userService.createUser("post@example.com", "Password", "admin", "John", "Doe");
        return userService.findByEmail("post@example.com");
    }
    
    @Test
    public void shouldCreateMovie() {
        UUID id = movieService.createMovie("Pulp Fiction", "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                           Collections.singleton("Drama"),
                                           "Quentin Tarantino");
        assertNotNull(id);
    }

    @Test
    public void shouldHaveNoRatings() {
        UUID id = createMovie();

        List<Rating> ratings = movieService.findAllRatings(id);
        assertEquals(0, ratings.size());
    }

    @Test
    public void shouldRateMovie() {
        User user = createUser();
        UUID movieId = createMovie();

        movieService.rateMovie(user.getId(), movieId, 5, "Comment");

        List<Rating> ratings = movieService.findAllRatings(movieId);
        assertEquals(1, ratings.size());
        assertEquals(5, ratings.get(0).getRating());
        assertEquals("Comment", ratings.get(0).getReview());

        movieService.rateMovie(user.getId(), movieId, 2, "Different comment");

        ratings = movieService.findAllRatings(movieId);
        assertEquals(1, ratings.size());
        assertEquals(2, ratings.get(0).getRating());
        assertEquals("Different comment", ratings.get(0).getReview());
    }

    @Test
    public void shouldComputeAvgRatingForSingleMovie() {
        User user = createUser();
        UUID movieId = createMovie();

        movieService.rateMovie(user.getId(), movieId, 5, "Comment");

        Movie movie = movieRepository.findById(movieId).orElse(null);

        assertEquals(0.0, movie.getAvgRating());
        movie = movieService.computeRating(movie);

        assertEquals(5.0, movie.getAvgRating());
    }

    @Test
    public void shouldReturnMoviesOrderedByAvgRating() {
        User user = createUser();
        UUID movieId1 = createMovie();
        UUID movieId2 = createMovie();

        movieService.rateMovie(user.getId(), movieId1, 1, "Comment");
        movieService.rateMovie(user.getId(), movieId2, 5, "Comment");

        List<Movie> movies = movieService.findAll();

        assertEquals(2, movies.size());
        assertEquals(movieId1, movies.get(1).getId());
        assertEquals(movieId2, movies.get(0).getId());
    }

    @Test
    public void shouldReturnNullIfInvalidDateStringGiven() {
        assertNull(movieService.createMovie("", "", "", "jbbjjkkjasjkdf", 5, Collections.EMPTY_SET, ""));
    }

    @Test
    public void shouldListAllGenres() {
        movieService.createMovie(faker.book().title(), "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                 Collections.singleton("Drama"),
                                 "Quentin Tarantino");
        movieService.createMovie(faker.book().title(), "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                 new HashSet<>(Arrays.asList("Crime", "Drama")),
                                 "Quentin Tarantino");
        movieService.createMovie(faker.book().title(), "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                 new HashSet<>(Arrays.asList("Documentary", "Nature")),
                                 "Quentin Tarantino");

        Set<String> genres = movieService.findAllGenres();

        assertTrue(genres.contains("Drama"));
        assertTrue(genres.contains("Crime"));
        assertTrue(genres.contains("Documentary"));
        assertTrue(genres.contains("Nature"));
    }
}
