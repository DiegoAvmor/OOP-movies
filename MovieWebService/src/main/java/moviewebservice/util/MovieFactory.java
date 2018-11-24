package moviewebservice.util;

import moviewebservice.model.Genre;
import moviewebservice.model.Movie;
import moviewebservice.service.GenreService;
import moviewebservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <h>Clase con metodos que permite la fabricacion de las peliculas e asignacion de los
 * ratings correspondientes a cada uno.
 */
@Component
public class MovieFactory {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private GenreService genreService;
    
    /**
     * <p> Metodo que le asigna los ratings a la pelicula que se le da como parametro
     * @param movie Objeto pelicula al cual se le asignara los ratings.
     * @return Devuelve un objeto pelicula con Ratings ya incluidos.
     */
    public Movie initRating(Movie movie) {
        movie.setRating(ratingService.getRatingByMovieId(movie.getMovieId()));
        return movie;
    }
    /**
     * <p> Metodo que devuelve una lista de peliculas con sus ratings de cada uno.
     * @param movies lista de peliculas al cual se le asignara sus ratings correspondientes.
     * @return Devuelve una lista de tipo Movie.
     */
    public List<Movie> initRatings(List<Movie> movies) {

        for(Movie movie : movies)
            movie = initRating(movie);

        return movies;
    }
    
    /**
     * <p> Metodo que recibe la version cruda del Objeto Movie, realiza acciones correspondientes para darle
     * mejor forma y devuelve el objeto tipo Movie.
     * @param movieRaw Objeto tipo MovieRaw que se usara para generar el Objeto Movie.
     * @return Devuelve el Objeto tipo Movie
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
