package webservice.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Movie;
import webservice.service.MovieService;
import webservice.util.MovieFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


 /**
 * Clase que realiza el manejo de las acciones entorno a 
 * las peliculas.
 */
@RestController
@RequestMapping("movies")
@CacheConfig(cacheNames={"movies"})
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieFactory movieFactory;
    
    
     /**
    * Metodo cuya funcion es el obtener una pelicula en especial por medio del id
    * de este.
    * @param id identificador de la pelicula.
    */
    @Cacheable(key="#id")
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieFactory.initRating(movieService.getMovieById(id));
    }
    
     /**
    * Metodo cuya funcion es realizar la obtencion del poster de la pelicula
    * apartir del id de la pelicula.
    * @param id identificado de la pelicula.
    */
    @Cacheable(key="#id")
    @GetMapping(value = "/{id}/poster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPosterByMovieId(@PathVariable int id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        InputStream in = new URL(movie.getPoster_path()).openStream();
        return IOUtils.toByteArray(in);
    }
    
     /**
    * Metodo cuya funcion es obtener el listado de todas las peliculas almacenadas
    * en la base de datos.
    */
    @Cacheable
    @GetMapping
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieFactory.initRatings(movieService.getAllMovies());

        return movies;
    }
    
     /**
    * Metodo cuuya funcion es obtener las peliculas con un titulo similar a lo
    * ingresado en buscador.
    * @param filter String que sera utilizado para obtener las peliculas con titulos similares a este.
    */
    public List<Movie> getAllMovies(String filter ) {
        return movieFactory.initRatings(movieService.getAllMovies(filter));
    }
    
     /**
    * Metodo curya funcion es la obtencion de las peliculas con ids de generos
    * similares. Devuelve una lista de los las peliculas con generos similares.
    * @param ids lista de enteros que seran utilizados para realizar la busqueda de generos.
    */
    @Cacheable(key="#ids")
    @GetMapping("genres/{ids}")
    public List<Movie> getMoviesByGenres(List<Integer> ids) {
        return movieFactory.initRatings(movieService.getMoviesByGenreIds(ids));
    }
}
