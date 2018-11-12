package com.cervera.moviesservice.service;

import com.cervera.moviesservice.Repository.GenreRepository;
import com.cervera.moviesservice.model.GenreCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    @PostConstruct
    public void loadData() {
        if(genreRepository.findAll().size() == 0) {
            RestTemplate restTemplate = new RestTemplate();
            String APIKey = "272f8904aff5923c030b53292132aec4";

            GenreCollection genres = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/genre/movie/list?api_key=" +
                    APIKey + "&language=en-US", GenreCollection.class);

            genreRepository.saveAll(genres.getGenres());
        }
    }

}
