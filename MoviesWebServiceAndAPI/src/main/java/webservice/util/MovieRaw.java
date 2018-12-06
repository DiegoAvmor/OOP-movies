package webservice.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de mapeo que recibe el JSON tal como es obtenido de
 * https://www.themoviedb.org/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRaw {
    private Integer id;
    private String title;
    private String overview;
    private String poster_path;
    private ArrayList<Integer> genre_ids = new ArrayList<>();

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

