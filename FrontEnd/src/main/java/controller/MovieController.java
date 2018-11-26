package controller;

import com.example.model.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import com.example.service.GenreService;
import com.example.service.MovieService;
import util.MovieFactory;
//import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieController {
    
    //@Autowired
    private MovieService movieService= new MovieService();

    @Autowired
    private MovieFactory movieFactory= new MovieFactory();

    public Movie getMovie( int id) {
        return movieFactory.initRating(movieService.getMovieById(id));
    }

    /*@GetMapping(value = "/{id}/poster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPosterByMovieId(@PathVariable int id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        InputStream in = new URL(movie.getPoster_path()).openStream();
        return IOUtils.toByteArray(in);
    }*/

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieFactory.initRatings(movieService.getAllMovies());

        return movies;
    }

    public List<Movie> getMoviesByGenres(List<Integer> ids) {
        return movieFactory.initRatings(movieService.getMoviesByGenreIds(ids));
    }
}
