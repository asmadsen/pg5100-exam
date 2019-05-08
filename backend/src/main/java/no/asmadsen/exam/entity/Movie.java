package no.asmadsen.exam.entity;


import org.hibernate.annotations.Type;
import org.hibernate.type.TextType;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.Collectors;

@NamedNativeQueries({
        @NamedNativeQuery(name = Movie.FIND_ALL_GENRES, query = "select distinct genres from movie_genres"),
        @NamedNativeQuery(name = Movie.FIND_ALL_BY_GENRES, query = "select m.* from (select count(g.movie_id) as matches, g.movie_id from movie_genres as g where g.genres in :genres group by g.movie_id) as c join movies as m on m.id = c.movie_id where c.matches = :count", resultClass = Movie.class)
})
@Entity
@Table(name = "movies")
public class Movie {
    public static final String FIND_ALL_GENRES = "FIND_ALL_GENRES";
    public static final String FIND_ALL_BY_GENRES = "FIND_ALL_BY_GENRES";
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String title;

    @URL
    private String image;

    @NotBlank
    private String plot;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> genres;

    @Past
    @NotNull
    private Date released;

    @Min(0)
    @NotNull
    private Integer runtime;

    @NotBlank
    @Size(max = 255)
    private String director;

    @Transient
    private double avgRating;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public Set<String> getFirst3Genres() {
        return genres.stream().limit(3).collect(Collectors.toSet());
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
