package moviewebservice.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <h>Clase mapeada a la tabla movies_genres que consite de unicamente un atributo
 * tipo MovieGenreId.
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
    
    /**
     * <p>Clase modelo de solo dos atributos que sirve para mapear los ids de peliculas
     * a los respectivos ids de genero que estos tienen.
     */
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
