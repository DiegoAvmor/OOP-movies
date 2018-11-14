package com.cervera.moviesservice.model;

import com.cervera.moviesservice.Repository.GenreRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRawCollection {
    private ArrayList<MovieRaw> results = new ArrayList<>();

    public void initGenreRepository(GenreRepository genreRepository) {
        for(MovieRaw movieRaw : results)
            movieRaw.setGenreRepository(genreRepository);
    }

    public MovieCollection buildMovieCollection() {
        MovieCollection movieCollection = new MovieCollection();

        for(MovieRaw movieRaw : results)
            movieCollection.getResults().add(movieRaw.buildMovie());

        return movieCollection;
    }

    public ArrayList<MovieRaw> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieRaw> results) {
        this.results = results;
    }
}
