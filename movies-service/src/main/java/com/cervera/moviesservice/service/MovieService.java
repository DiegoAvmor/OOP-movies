package com.cervera.moviesservice.service;

import com.cervera.moviesservice.Repository.GenreRepository;
import com.cervera.moviesservice.Repository.MovieRepository;
import com.cervera.moviesservice.model.*;
import com.cervera.moviesservice.model.MovieCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @PostConstruct
    public void loadData() {
        if(movieRepository.findAll().size() == 0) {
            int pages = 1;

            RestTemplate restTemplate = new RestTemplate();
            String APIKey = "272f8904aff5923c030b53292132aec4";

            for(int i = 1; i <= pages; i++) {
                MovieRawCollection movieRawCollection = restTemplate.getForObject(
                        "https://api.themoviedb.org/3/movie/top_rated?api_key="+
                                APIKey +"&language=en-US&page=" + i,
                        MovieRawCollection.class
                );

                movieRawCollection.initGenreRepository(genreRepository);

                movieRepository.saveAll(movieRawCollection.
                        buildMovieCollection().getResults());
            }
        }
    }
}
