package webservice.service;

import com.jayway.jsonpath.TypeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.Genre;
import webservice.repository.GenreRepository;
import webservice.util.JsonPathUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @PostConstruct
    public void loadData() {
        if(genreRepository.findAll().size() == 0) {
            try {
                String APIKey = "272f8904aff5923c030b53292132aec4";

                URL jsonURL = new URL("https://api.themoviedb.org/3/genre/movie/list?api_key=" +
                        APIKey + "&language=en-US");

                List<Genre> genres = JsonPathUtil.getMappedEntities(jsonURL, "$.genres",
                        new TypeRef<List<Genre>>(){});

                genreRepository.saveAll(genres);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Integer genre_id) {
        return genreRepository.findById(genre_id).get();
    }
}
