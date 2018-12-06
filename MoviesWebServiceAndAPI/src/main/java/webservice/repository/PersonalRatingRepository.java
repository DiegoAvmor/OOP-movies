package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webservice.model.PersonalRating;
import webservice.util.PersonalRatingId;

import java.util.List;

/**
 * Proporciona métodos CRUD mediante la interfaz {@link JpaRepository}
 */
@Repository
public interface PersonalRatingRepository extends JpaRepository<PersonalRating, PersonalRatingId> {
    /**
     * @param username Nombre de usuario a buscar.
     * @return Todos los objetos {@link PersonalRating} cuyo nombre de usuario sea el especificado.
     */
    @Query("select o from PersonalRating o where o.personalRatingId.username = :username")
    List<PersonalRating> getAllByUsername(@Param("username") String username);
}
