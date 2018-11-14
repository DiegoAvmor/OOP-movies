package moviewebservice.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import moviewebservice.model.Genre;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreCollection {
    private ArrayList<Genre> genres = new ArrayList<>();

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}