package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.controller.AccountController;
import webservice.model.PersonalRating;
import webservice.repository.AccountRepository;
import webservice.repository.PersonalRatingRepository;
import webservice.util.PersonalRatingId;

import java.util.List;

/**
 * Provee una capa de separación entre su controlador (aún no implementado) y
 * le interfaz {@link PersonalRatingRepository}.
 */
@Service
public class PersonalRatingService {
    @Autowired
    PersonalRatingRepository personalRatingRepository;

    /**
     * Modifica un rating personal existente.
     *
     * @param personalRating Valor del nuevo rating personal.
     */
    public void updateRating(PersonalRating personalRating) {
        personalRatingRepository.deleteById(personalRating.getPersonalRatingId());
        personalRatingRepository.save(personalRating);
    }

    /**
     *  Almacena un nuevo rating personal.
     *
     * @param personalRating valor del rating personal a almacenar.
     */
    public void saveRating(PersonalRating personalRating) {
        personalRatingRepository.save(personalRating);
    }

    /**
     * Identifica si un rating personal existe.
     *
     * @param personalRating Valor del rating personal por verificar existencia.
     * @return Si existe: true; si no: false.
     */
    public boolean exists(PersonalRating personalRating) {
        return personalRatingRepository.existsById(personalRating.getPersonalRatingId());
    }

    /**
     * @param id Identificador del rating personal solicitado.
     * @return Rating personal solicitado.
     */
    public PersonalRating findById(PersonalRatingId id) {
        return personalRatingRepository.findById(id).get();
    }

    /**
     * Identifica si un rating personal existe.
     *
     * @param id Identificador del rating personal por verificar existencia.
     * @return Si existe: true; si no: false.
     */
    public boolean existsById(PersonalRatingId id) {
        return personalRatingRepository.existsById(id);
    }

    /**
     * Devuelve todos los ratings personales de una cuenta.
     *
     * @param username Nombre de usuario asociado a los ratings solicitados.
     * @return Lista de {@link PersonalRating} solicitados
     */
    public List<PersonalRating> getAllByUsername(String username) {
        return personalRatingRepository.getAllByUsername(username);
    }
}
