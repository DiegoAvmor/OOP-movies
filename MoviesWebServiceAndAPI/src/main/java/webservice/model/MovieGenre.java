package webservice.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


 /**
 * Clase anotada como entidad y mapeada a la tabla movies_genres en la base de datos.
 */
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
}
