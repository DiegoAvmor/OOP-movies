package moviewebservice.service;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import moviewebservice.model.Movie;
import moviewebservice.repository.GenreRepository;
import moviewebservice.repository.MovieGenreRepository;
import moviewebservice.repository.MovieRepository;
import moviewebservice.util.JsonPathUtil;
import moviewebservice.util.MovieRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @Autowired
    RatingService ratingService;

    @PostConstruct
    public void loadData() {
        if(movieRepository.findAll().size() == 0) {
            int pages = 1;

            RestTemplate restTemplate = new RestTemplate();
            String APIKey = "272f8904aff5923c030b53292132aec4";

            for(int i = 1; i <= pages; i++) {

                try {
                    URL jsonURL = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key="+
                                APIKey +"&language=en-US&page=" + i);

                    List<MovieRaw> movieRaws =
                            JsonPathUtil.getMappedEntities(jsonURL, "$.results[*]",
                                            new TypeRef<List<MovieRaw>>(){});

                    List<Movie> movies = new ArrayList<>();

                    for(MovieRaw movieRaw : movieRaws) {
                        movieRaw.setGenreRepository(genreRepository);
                        movies.add(movieRaw.buildMovie());
                    }

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
