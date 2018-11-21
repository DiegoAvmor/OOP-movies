package moviewebservice.util;

import moviewebservice.model.Movie;
import moviewebservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieFactory {

    @Autowired
    RatingService ratingService;

    public Movie initRating(Movie movie) {
        movie.setRating(ratingService.getRatingByMovieId(movie.getId()));
        return movie;
    }
}
