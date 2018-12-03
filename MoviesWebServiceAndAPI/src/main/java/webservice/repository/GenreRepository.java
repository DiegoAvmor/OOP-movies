package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
