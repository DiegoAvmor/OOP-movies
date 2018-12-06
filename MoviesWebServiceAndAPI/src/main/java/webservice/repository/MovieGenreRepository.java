package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webservice.model.Genre;
import webservice.model.Movie;
import webservice.model.MovieGenre;

import java.util.List;

/**
 * Proporciona métodos CRUD mediante la interfaz {@link JpaRepository}
 */
@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenre.MovieGenreId> {
    /**
     * @param ids ids de los géneros que las películas deben contener.
     * @param size número de géneros.
     * @return lista de ids de objetos {@link Movie} con un subconjunto de los géneros especificados.
     */
    @Query("select distinct o.movieGenreId.movie_id " +
            "from MovieGenre o where genres_id in :ids " +
            "group by o.movieGenreId.movie_id having count(o) = :size")
    List<Integer> findMovieIdByGenreIds(@Param("ids") List<Integer> ids, @Param("size") Long size);
}
