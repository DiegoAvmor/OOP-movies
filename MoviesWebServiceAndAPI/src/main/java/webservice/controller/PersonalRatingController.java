package webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.PersonalRating;
import webservice.service.PersonalRatingService;
import webservice.util.PersonalRatingId;

import java.util.List;
 /**
 * Clase cuya funcion es realizar las acciones correspondientes
 * al manejo de los ratings personales de los usuarios.
 */
@Component
public class PersonalRatingController {
    @Autowired
    private PersonalRatingService personalRatingService;
    
     /**
    * Metodo cuya funcion es realizar la carga de los ratings personales
    * del usuario.
    */
    public void loadRating(PersonalRating personalRating) {
        if(personalRatingService.exists(personalRating))
            personalRatingService.updateRating(personalRating);
        else
            personalRatingService.saveRating(personalRating);
    }
    
     /**
    * Metodo que verifica la existencia de los ratings personales
    * del usuario.
    * @param id Objeto PersonalRatingId el sera usado como identificador para comprobar su existencia
    */
    public boolean existsById(PersonalRatingId id) {
        return personalRatingService.existsById(id);
    }
    
     /**
    * Metodo cuya funcion es la obtencion del rating personal de un usuario.
    * @param personalRatingId identificador que sera usado para realizar la busqueda.
    */
    public PersonalRating getPersonalRatingById(PersonalRatingId personalRatingId) {
        return personalRatingService.findById(personalRatingId);
    }
    
     /**
    * Metodo cuya funcion es el obtener los ratings de usuario por medio del
    * nombre del usuario.
    * @param username String que sera usado para realizar la busqueda de los ratings personales.
    */
    public List<PersonalRating> getPersonalRatingsByUsername(String username) {
        return personalRatingService.getAllByUsername(username);
    }
}
