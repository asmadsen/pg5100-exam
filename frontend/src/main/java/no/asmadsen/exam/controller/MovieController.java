package no.asmadsen.exam.controller;

import no.asmadsen.exam.Utils;
import no.asmadsen.exam.entity.Movie;
import no.asmadsen.exam.repository.MovieRepository;
import no.asmadsen.exam.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class MovieController {
    @Autowired
    private EntityManager em;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private Utils utils;

    private List<String> genres;

    public Movie findById(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception ignored) {
            return null;
        }
        Movie movie = movieRepository.findById(uuid).orElse(null);
        if (movie != null) {
            movie = movieService.computeRating(movie);
        }
        return movie;
    }

    public Set<String> getAllGenres() {
        return movieService.findAllGenres();
    }

    public String getUrlGenres() {
        if (genres == null) return "";
        return String.join(",", genres);
    }

    public void setUrlGenres(String input) {
        genres = Arrays.asList(input.split(","))
            .stream().filter(entry -> !entry.isEmpty())
                       .collect(Collectors.toList());
    }

    public boolean genreIsChecked(String genre) {
        if (genres == null) return false;
        return genres.contains(genre);
    }

    public List<Iterable<Movie>> findAll() {
        if (genres == null || genres.isEmpty()) {
            return utils.groupIntoRows(movieService.findAll());
        }

        return utils.groupIntoRows(em.createNamedQuery(Movie.FIND_ALL_BY_GENRES, Movie.class)
                                   .setParameter("genres", genres)
                            .setParameter("count", genres.size())
                .getResultList());
    }
}
