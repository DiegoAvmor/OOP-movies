package MovieRating.Service.controller;

import MovieRating.Service.model.Movie;
import MovieRating.Service.service.MovieService;
import MovieRating.Service.service.bean.MovieFilteringBean;
import java.util.List;
import javax.ws.rs.BeanParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**<h>Clase el cual por medio del uso del framework de SpringBoot realiza las acciones
 * de un REST API en base al tipo de metodo Http que se elija.</h>
 *
 * @author Diego
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    
    MovieService movieserv= new MovieService();
    /**
     * <p>Metodo que devuelve una pelicula apartir de su id correspondiente.
     * @see MovieService#getMovieFromId(int) 
     * @param id
     * @return Devuelve el objeto "Movie"
     */
    @GetMapping("{id}")//realizara el metodo HTTP GET en la direccion ..../movie/{id} *{} simboliza un paramtro que es variable
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovie(@PathVariable int id)
    {
        Movie movie= movieserv.getMovieFromId(id);
        return movie;
    }
    
    /**
     * <p>Metodo que devuelve una lista de todas las peliculas almacenadas, la forma en que devuelve la lista
     * cambia dependiendo del uso de los parametros en la url.
     * @param filter
     * @see MovieService#getAllMovies()
     * @see MovieService#getAllMoviesForPagination(int, int) 
     * @return Devuelve la lista de las peliculas almacenadas dependiendo de los parametros en la url que se hayan introducido
     */
    @GetMapping// realiza el metodo http GET  en la direccion .../movie(muestra todo el listado) o .../movie?start=[valor int]&size=[valor int]
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies(@BeanParam MovieFilteringBean filter)//@RequestParam(defaultValue="1") int start,@RequestParam(defaultValue="10")int size
    {
        //Si los paramtroe start y size tiene un valor entonces muestra las peliculas en forma de paginacion
        if(filter.getStart()>=0 && filter.getSize()>0)
        {
            return movieserv.getAllMoviesForPagination(filter.getStart(), filter.getSize());
        }
        //En caso contrario solo muestra todas las peliculas
        else return movieserv.getAllMovies();
    }
}
