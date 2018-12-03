package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webservice.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select o from Movie o where o.title like %:filter%")
    List<Movie> findAll(@Param("filter") String filter);
}
