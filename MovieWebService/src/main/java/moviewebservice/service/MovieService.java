package moviewebservice.service;

import moviewebservice.model.Movie;
import moviewebservice.model.MovieGenre;
import moviewebservice.repository.GenreRepository;
import moviewebservice.repository.MovieGenreRepository;
import moviewebservice.repository.MovieRepository;
import moviewebservice.util.MovieRawCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    private List<MovieGenre> getMovieIdsByGenreIds(List<Integer> ids) {
        return movieGenreRepository.findMovieIdByGenreIds(ids);
    }

    public List<Movie> getMoviesByGenreIds(List<Integer> ids) {
        List<MovieGenre> movieGenreFiltered = getMovieIdsByGenreIds(ids);
        List<Movie> movies = new ArrayList<>();

        for(MovieGenre movieGenre : movieGenreFiltered)
            if(!movies.contains(movieRepository.findById(movieGenre.getMovieGenreId().getMovie_id()).get()))
                movies.add(movieRepository.findById(movieGenre.getMovieGenreId().getMovie_id()).get());

        return movies;
    }
}
