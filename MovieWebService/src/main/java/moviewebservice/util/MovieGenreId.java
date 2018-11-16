package moviewebservice.util;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieGenreId implements Serializable {
    private int movie_id;
    private int genres_id;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getGenres_id() {
        return genres_id;
    }

    public void setGenres_id(int genres_id) {
        this.genres_id = genres_id;
    }

    @Override
    public String toString() {
        return "MovieGenreId{" +
                "movie_id=" + movie_id +
                ", genres_id=" + genres_id +
                '}';
    }
}
