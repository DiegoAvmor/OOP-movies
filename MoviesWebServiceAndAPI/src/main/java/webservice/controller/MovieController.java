package webservice.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Movie;
import webservice.service.MovieService;
import webservice.util.MovieFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movies")
@CacheConfig(cacheNames={"movies"})
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieFactory movieFactory;

    @Cacheable(key="#id")
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieFactory.initRating(movieService.getMovieById(id));
    }

    @Cacheable(key="#id")
    @GetMapping(value = "/{id}/poster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPosterByMovieId(@PathVariable int id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        InputStream in = new URL(movie.getPoster_path()).openStream();
        return IOUtils.toByteArray(in);
    }

    @Cacheable
    @GetMapping
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieFactory.initRatings(movieService.getAllMovies());

        return movies;
    }

    public List<Movie> getAllMovies(String filter ) {
        return movieFactory.initRatings(movieService.getAllMovies(filter));
    }

    @Cacheable(key="#ids")
    @GetMapping("genres/{ids}")
    public List<Movie> getMoviesByGenres(List<Integer> ids) {
        return movieFactory.initRatings(movieService.getMoviesByGenreIds(ids));
    }
}
