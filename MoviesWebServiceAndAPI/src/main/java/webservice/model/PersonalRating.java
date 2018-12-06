package webservice.model;

import webservice.util.PersonalRatingId;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "personal_ratings")
public class PersonalRating {

    @EmbeddedId
    private PersonalRatingId personalRatingId;
    private Integer rating;

    public PersonalRating() {

    }

    public PersonalRating(PersonalRatingId personalRatingId, Integer rating) {
        this.personalRatingId = personalRatingId;
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public PersonalRatingId getPersonalRatingId() {
        return personalRatingId;
    }

    public void setPersonalRatingId(PersonalRatingId personalRatingId) {
        this.personalRatingId = personalRatingId;
    }
}
