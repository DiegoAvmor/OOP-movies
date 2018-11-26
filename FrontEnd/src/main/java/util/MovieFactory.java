package util;

import com.example.model.Genre;
import com.example.model.Movie;
import com.example.service.GenreService;
import com.example.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieFactory {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private GenreService genreService;

    public Movie initRating(Movie movie) {
        movie.setRating(ratingService.getRatingByMovieId(movie.getId()));
        return movie;
    }

    public List<Movie> initRatings(List<Movie> movies) {

        for(Movie movie : movies)
            movie = initRating(movie);

        return movies;
    }

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
