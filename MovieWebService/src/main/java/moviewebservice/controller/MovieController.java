package moviewebservice.controller;

import moviewebservice.repository.MovieRepository;
import moviewebservice.service.MovieService;
import moviewebservice.model.Movie;
import moviewebservice.service.bean.MovieFilteringBean;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.BeanParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**<h>Clase el cual por medio del uso del framework de SpringBoot realiza las acciones
 * de un REST API en base al tipo de metodo Http que se elija.</h>
 *
 * @author Diego y Hernán
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    GenreRepository genreRepository;
    
    @Autowired
    MovieRepository movieRepository;
    /**
     * <p>Metodo que devuelve una pelicula apartir de su id correspondiente.
     * @param id
     * @return Devuelve el objeto "Movie"
     */
    @GetMapping("{id}")//realizara el metodo HTTP GET en la direccion ..../movie/{id} *{} simboliza un paramtro que es variable
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovie(@PathVariable int id)
    {
        return movieRepository.findById(id).get();
    }
    
    /**
     * <p>Metodo que devuelve una lista de todas las peliculas almacenadas, la forma en que devuelve la lista
     * cambia dependiendo del uso de los parametros en la url.
     * @return Devuelve la lista de las peliculas almacenadas dependiendo de los parametros en la url que se hayan introducido
     */
    @GetMapping// realiza el metodo http GET  en la direccion .../movie(muestra todo el listado) o .../movie?start=[valor int]&size=[valor int]
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies()//@RequestParam(defaultValue="1") int start,@RequestParam(defaultValue="10")int size
    {
        return movieRepository.findAll();
    }
}
