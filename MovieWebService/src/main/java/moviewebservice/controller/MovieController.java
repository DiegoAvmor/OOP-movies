package moviewebservice.controller;

import moviewebservice.model.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import moviewebservice.service.GenreService;
import moviewebservice.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    GenreService genreService;
    
    @Autowired
    MovieService movieService;

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping(value = "/{id}/poster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPosterByMovieId(@PathVariable int id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        InputStream in = new URL(movie.getPoster_path()).openStream();
        return IOUtils.toByteArray(in);
    }
    
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genres/{ids}")
    public List<Movie> getMoviesByGenres(@PathVariable List<Integer> ids) {
        return movieService.getMoviesByGenreIds(ids);
    }
}
