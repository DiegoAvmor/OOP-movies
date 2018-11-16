package moviewebservice.controller;

import moviewebservice.model.Genre;
import moviewebservice.model.Movie;
import java.util.List;
import moviewebservice.service.GenreService;
import moviewebservice.service.MovieGenreService;
import moviewebservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class SystemController {

    @Autowired
    GenreService genreService;
    
    @Autowired
    MovieService movieService;

    @Autowired
    MovieGenreService movieGenreService;

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/genres/{ids}")
    public List<Movie> getMoviesByGenres(@PathVariable List<Integer> ids) {
        return movieService.getMoviesByGenreIds(ids);
    }
}
