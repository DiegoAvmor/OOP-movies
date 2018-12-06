package webservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.Genre;
import webservice.model.Movie;
import webservice.model.Rating;
import webservice.service.GenreService;
import webservice.service.RatingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Fábrica de objetos de {@link Movie}. Agrega una lista de objetos {@link Genre}
 * e inicializa los {@link Rating}.
 */

@Component
public class MovieFactory {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private GenreService genreService;

    /**
     * Inicializa el objeto {@link Rating} de una objeto {@link Movie}.
     */
    public Movie initRating(Movie movie) {
        movie.setRating(ratingService.getRatingByMovieId(movie.getId()));
        return movie;
    }

    /**
     * Inicializa el objeto {@link Rating} de una lista de objetos {@link Movie}.
     *
     * @param movies lista de objetos {@link Movie}
     * @return una lista de objetos {@link Movie} con {@link Rating} inicializados.
     */
    public List<Movie> initRatings(List<Movie> movies) {

        for(Movie movie : movies)
            movie = initRating(movie);

        return movies;
    }

    /**
     * Inicializa la lista de objetos {@link Genre} en los objetos {@link Movie}.
     *
     * @param movieRaw
     * @return una lista de objetos {@link Movie} con objetos {@link Genre} inicializados.
     */
    public Movie buildMovie(MovieRaw movieRaw) {
        List<Genre> genres = new ArrayList<>();

        for(Integer genre_id : movieRaw.getGenre_ids())
            genres.add(genreService.getGenreById(genre_id));

        movieRaw.setPoster_path("https://image.tmdb.org/t/p/w500" + movieRaw.getPoster_path());

        return new Movie(
                movieRaw.getId(),
                movieRaw.getTitle(),
                movieRaw.getOverview(),
                movieRaw.getPoster_path(),
                genres);
    }
}
