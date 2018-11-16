package moviewebservice.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import moviewebservice.util.MovieGenreId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movies_genres")
public class MovieGenre {
    @EmbeddedId
    MovieGenreId movieGenreId;

    public MovieGenreId getMovieGenreId() {
        return movieGenreId;
    }

    public void setMovieGenreId(MovieGenreId movieGenreId) {
        this.movieGenreId = movieGenreId;
    }

    @Override
    public String toString() {
        return "MovieGenre{" +
                "movieGenreId=" + movieGenreId +
                '}';
    }
}
