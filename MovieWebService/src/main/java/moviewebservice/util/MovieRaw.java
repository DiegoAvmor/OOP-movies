package moviewebservice.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import moviewebservice.model.Genre;
import moviewebservice.model.Movie;
import moviewebservice.model.Rating;
import moviewebservice.repository.GenreRepository;
import moviewebservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRaw {
    private Integer id;
    private String title;
    private String overview;
    private String poster_path;
    private ArrayList<Integer> genre_ids = new ArrayList<>();

    GenreRepository genreRepository;

    public Movie buildMovie() {
        List<Genre> genres = new ArrayList<>();

        for(Integer genre_id : genre_ids) {
            Optional<Genre> optional = genreRepository.findById(genre_id);
            genres.add(optional.get());
        }

        poster_path = "https://image.tmdb.org/t/p/w500" + poster_path;

        return new Movie(id, title, overview, poster_path, genres);
    }

    public GenreRepository getGenreRepository() {
        return genreRepository;
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public String toString() {
        return "MovieRaw{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", genre_ids=" + genre_ids +
                '}';
    }
}

