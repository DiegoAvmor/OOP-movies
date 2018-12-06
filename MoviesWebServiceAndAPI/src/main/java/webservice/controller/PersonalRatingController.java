package webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.PersonalRating;
import webservice.service.PersonalRatingService;
import webservice.util.PersonalRatingId;

import java.util.List;

@Component
public class PersonalRatingController {
    @Autowired
    private PersonalRatingService personalRatingService;

    public void loadRating(PersonalRating personalRating) {
        if(personalRatingService.exists(personalRating))
            personalRatingService.updateRating(personalRating);
        else
            personalRatingService.saveRating(personalRating);
    }

    public boolean existsById(PersonalRatingId id) {
        return personalRatingService.existsById(id);
    }

    public PersonalRating getPersonalRatingById(PersonalRatingId personalRatingId) {
        return personalRatingService.findById(personalRatingId);
    }

    public List<PersonalRating> getPersonalRatingsByUsername(String username) {
        return personalRatingService.getAllByUsername(username);
    }
}
