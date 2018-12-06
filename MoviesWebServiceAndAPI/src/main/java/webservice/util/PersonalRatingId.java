package webservice.util;

import webservice.model.PersonalRating;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Clase que serializa el id compuesto de la clase {@link PersonalRating}
 */
@Embeddable
public class PersonalRatingId implements Serializable {
    private Integer id;
    @Column(length = 30)
    private String username;

    public PersonalRatingId() {

    }

    public PersonalRatingId(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
