package moviewebservice.service;

import moviewebservice.repository.GenreRepository;
import moviewebservice.util.GenreCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
