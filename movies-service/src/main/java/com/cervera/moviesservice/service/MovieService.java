package com.cervera.moviesservice.service;

import com.cervera.moviesservice.Repository.MovieRepository;
import com.cervera.moviesservice.model.*;
import com.cervera.moviesservice.model.MovieCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @PostConstruct
    public void loadData() {
        if(movieRepository.findAll().size() == 0) {
            int pages = 1;

            RestTemplate restTemplate = new RestTemplate();
            String APIKey = "272f8904aff5923c030b53292132aec4";

            for(int i = 1; i <= pages; i++) {
                MovieIdCollection ids = restTemplate.getForObject(
                        "https://api.themoviedb.org/3/movie/top_rated?api_key="+
                                APIKey +"&language=en-US&page=" + i,
                        MovieIdCollection.class
                );

                MovieCollection result = new MovieCollection();

                Movie movie;

                for(MovieId id : ids.getResults()) {
                    movie = restTemplate.getForObject(
                            "https://api.themoviedb.org/3/movie/" + id.getId()
                                    + "?api_key=" + APIKey,
                            Movie.class
                    );

                    result.getResults().add(movie);
                }

                movieRepository.saveAll(result.getResults());
            }
        }
    }
}
