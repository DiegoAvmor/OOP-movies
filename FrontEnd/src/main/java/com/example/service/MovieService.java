package com.example.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Movie;
import com.example.repository.MovieGenreRepository;
import com.example.repository.MovieRepository;
import com.jayway.jsonpath.TypeRef;

import util.JsonPathUtil;
import util.MovieFactory;
import util.MovieRaw;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Autowired
    private  MovieFactory movieFactory;

    @PostConstruct
    public void loadData() {
        if(movieRepository.findAll().size() == 0) {
            int pages = 1;

            String APIKey = "272f8904aff5923c030b53292132aec4";

            for(int i = 1; i <= pages; i++) {

                try {
                    URL jsonURL = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key="+
                                APIKey +"&language=en-US&page=" + i);

                    List<MovieRaw> movieRaws =
                            JsonPathUtil.getMappedEntities(jsonURL, "$.results[*]",
                                            new TypeRef<List<MovieRaw>>(){});

                    List<Movie> movies = new ArrayList<>();

                    for(MovieRaw movieRaw : movieRaws)
                        movies.add(movieFactory.buildMovie(movieRaw));

                    movieRepository.saveAll(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    private List<Integer> getMovieIdsByGenreIds(List<Integer> ids, Long size) {
        return movieGenreRepository.findMovieIdByGenreIds(ids, new Long(ids.size()));
    }

    public List<Movie> getMoviesByGenreIds(List<Integer> ids) {
        List<Movie> movies = new ArrayList<>();

        for(Integer movieId : getMovieIdsByGenreIds(ids, new Long(ids.size())))
                movies.add(movieRepository.findById(movieId).get());

        return movies;
    }
}
