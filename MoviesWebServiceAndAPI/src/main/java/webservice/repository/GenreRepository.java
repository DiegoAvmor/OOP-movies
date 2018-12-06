package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.model.Genre;

/**
 * Proporciona métodos CRUD mediante la interfaz {@link JpaRepository}
 */
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
