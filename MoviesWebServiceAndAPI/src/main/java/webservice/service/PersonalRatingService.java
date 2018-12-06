package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.PersonalRating;
import webservice.repository.PersonalRatingRepository;
import webservice.util.PersonalRatingId;

import java.util.List;

@Service
public class PersonalRatingService {
    @Autowired
    PersonalRatingRepository personalRatingRepository;

    public void updateRating(PersonalRating personalRating) {
        personalRatingRepository.deleteById(personalRating.getPersonalRatingId());
        personalRatingRepository.save(personalRating);
    }

    public void saveRating(PersonalRating personalRating) {
        personalRatingRepository.save(personalRating);
    }

    public boolean exists(PersonalRating personalRating) {
        return personalRatingRepository.existsById(personalRating.getPersonalRatingId());
    }

    public PersonalRating findById(PersonalRatingId id) {
        return personalRatingRepository.findById(id).get();
    }

    public boolean existsById(PersonalRatingId id) {
        return personalRatingRepository.existsById(id);
    }

    public List<PersonalRating> getAllByUsername(String username) {
        return personalRatingRepository.getAllByUsername(username);
    }
}
