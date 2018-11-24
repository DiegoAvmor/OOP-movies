package moviewebservice.controller;

import moviewebservice.model.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import moviewebservice.service.MovieService;
import moviewebservice.util.MovieFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <h>Clase que es anotado como controlador rest mapeado a la direccion base "/api.v$"
 * que contiene los metodos necesarios para realizar de forma efectiva los metodos HTTP.
 */
@RestController
@RequestMapping("/api.v1")//Version actual de nuestra api
@CacheConfig(cacheNames={"movies"})
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @Autowired
    MovieFactory movieFactory;
    
    /**
     * <p>Metod mapeado al Metodo HTTP GET el cual devovlera una pelicula en especial
     * apartir del id que recibe como parametro.
     * @param id
     * @return Devuelve un Objeto tipo Movie.
     */
    @Cacheable(key="#id")
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id) {
        Movie movie= movieFactory.initRating(movieService.getMovieById(id));
        if(movie==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);}   
        movie.add(new Link("http://localhost:8080/api.v1/movies/"+id));
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }
    /**
     * <p>Metodo mapaeado al Metodo HTTP Get el cual apartir del parametro id, devolvera
     * la URL del poster.
     * @param id int que representa el id de la pelicula.
     * @return 
     * @throws IOException 
     */
    @Cacheable(key="#id")
    @GetMapping(value = "/movies/{id}/poster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPosterByMovieId(@PathVariable int id) throws IOException {
        Movie movie = movieService.getMovieById(id);
        InputStream in = new URL(movie.getPoster_path()).openStream();
        return IOUtils.toByteArray(in);
    }
    
    /**
     * <p>Metodo mapaeado al metodo HTTP GET en la URL "/movies" que devolvera en formato json el listado
     * de todas las peliculas presentes en la base de datos relacional.
     * @return Devuelve la lista completa de peliculas.
     */
    @Cacheable
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieFactory.initRatings(movieService.getAllMovies());
        for(Movie movie : movies){
            movie.add(new Link("http://localhost:8080/api.v1/movies/"+movie.getMovieId()));
        }
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    
    /**
     * <p>Metodo mapeado al Metodo HTTP GET en la ruta "/movies/genres/{ids}" el cual apartir
     * de los ids introducido en la ruta devolvera en formato json una lista de peliculas que comparten el mismo id
     * de genero.
     * @param ids lista de enteros que representan el identificador de los generos.
     * @return Devuelve una lista de peliculas con generos similares entre cada uno.
     */
    @Cacheable(key="#ids")
    @GetMapping("/movies/genres/{ids}")
    public ResponseEntity<List<Movie>> getMoviesByGenres(@PathVariable List<Integer> ids) {
        List<Movie> movies= movieFactory.initRatings(movieService.getMoviesByGenreIds(ids));
        for(Movie mov: movies){
            mov.add(new Link("http://localhost:8080/api.v1/movies/"+mov.getMovieId()));
        } 
        return new ResponseEntity<>(movieService.getMoviesByGenreIds(ids),HttpStatus.OK);
    }
}
