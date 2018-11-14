package moviewebservice.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import moviewebservice.model.Movie;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieCollection {
    private ArrayList<Movie> results = new ArrayList<>();

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
