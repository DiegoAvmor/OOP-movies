package moviewebservice.repository;

import moviewebservice.model.MovieGenre;
import moviewebservice.util.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {
    // from MovieGenre where genres_id in :ids
    @Query("select distinct o.movieGenreId.movie_id " +
            "from MovieGenre o where genres_id in :ids " +
            "group by o.movieGenreId.movie_id having count(o) = :size")
    List<Integer> findMovieIdByGenreIds(@Param("ids") List<Integer> ids,@Param("size") Long size);
}
