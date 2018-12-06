package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webservice.model.PersonalRating;
import webservice.util.PersonalRatingId;

import java.util.List;

@Repository
public interface PersonalRatingRepository extends JpaRepository<PersonalRating, PersonalRatingId> {
    @Query("select o from PersonalRating o where o.personalRatingId.username = :username")
    List<PersonalRating> getAllByUsername(@Param("username") String username);
}
