package moviewebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import moviewebservice.model.Genre;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private Integer id;
    private String title;
    @Column(length = 1800)
    private String overview;
    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    public Movie() {

    }

    public Movie(Integer id, String title, String overview, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getId() {
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
