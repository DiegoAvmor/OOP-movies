package moviewebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

/**
 * <h>Clase que esta mapeada a la tabla movies de la base de datos, consiste
 * unicamente de dos contructores siendo el constructor predeterminado uno de ellos.
 */
@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends ResourceSupport{
    @Id
    private Integer id;
    private String title;
    @Column(length = 1800)
    private String overview;
    @ManyToMany
    private List<Genre> genres = new ArrayList<>();
    private String poster_path;
    @Transient
    private Rating rating;

    public Movie() {

    }

    public Movie(Integer id, String title, String overview,
                 String poster_path, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.genres = genres;
    }
//-------------Sets and Gets-------------------
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public Integer getMovieId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", genres=" + genres +
                '}';
    }
}
