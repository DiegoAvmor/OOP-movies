package moviewebservice.service;

import com.jayway.jsonpath.TypeRef;
import moviewebservice.model.Movie;
import moviewebservice.repository.MovieGenreRepository;
import moviewebservice.repository.MovieRepository;
import moviewebservice.util.JsonPathUtil;
import moviewebservice.util.MovieFactory;
import moviewebservice.util.MovieRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <h>Clase que encuadra al la parte de los servicios del api entorno a las peliculas.
 * cuenta con metodos para obtener informacion por uso de servicios terceros, obtener
 * informacion de las peliculas desde la base de datos,etc.
 */
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Autowired
    private  MovieFactory movieFactory;
    
    /**
     * <p>Metodo que se ejecuta despues de la construccion de la aplicacion, su funcion
     * unica es la de obtener datos de las peliculas y almacenarlas en la base de datos.
     */
    @PostConstruct
    public void loadData() {
        if(movieRepository.findAll().size() == 0) {
            int pages = 1;

            RestTemplate restTemplate = new RestTemplate();
            String APIKey = "272f8904aff5923c030b53292132aec4";

            for(int i = 1; i <= pages; i++) {

                try {
                    URL jsonURL = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key="+
                                APIKey +"&language=en-US&page=" + i);

                    List<MovieRaw> movieRaws =
                            JsonPathUtil.getMappedEntities(jsonURL, "$.results[*]",
                                            new TypeRef<List<MovieRaw>>(){});

                    List<Movie> movies = new ArrayList<>();

                    for(MovieRaw movieRaw : movieRaws)
                        movies.add(movieFactory.buildMovie(movieRaw));

                    movieRepository.saveAll(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * <p> Metodo que se encarga de devolver en una lista todas las peliculas almacenadas
     * en la base de datos.
     * @return 
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    /**
     * <p> Metodo que recibe como parametro el identificador de la pelicula, se conecta a 
     * la base de datos y devuelve un objeto de tipo Movie correspondiente a su identificador.
     * @param id int que representa el identificador de pelicula.
     * @return Devuelve un Objeto tipo Movie
     */
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).get(); 
    }
    
    /**
     * <p> Metodo que devuevle una lista de enteros que representan los ids de los
     * generos de peliculas asociados a una pelicula.
     * @param ids Lista de enteros que representan los ids de generos.
     * @param size Tamaños de dicha lista
     * @return Devuelve la lista de los ids de generos.
     */
    private List<Integer> getMovieIdsByGenreIds(List<Integer> ids, Long size) {
        return movieGenreRepository.findMovieIdByGenreIds(ids, new Long(ids.size()));
    }
    
    /**
     * <p> Metodo que devuelve una lista de peliculas que comparte un o unos generos
     * similares.
     * @param ids lista de identificadores de generos.
     * @return Devuelve la lista de pelicula con generos similares entre cada uno.
     */
    public List<Movie> getMoviesByGenreIds(List<Integer> ids) {
        List<Movie> movies = new ArrayList<>();

        for(Integer movieId : getMovieIdsByGenreIds(ids, new Long(ids.size())))
                movies.add(movieRepository.findById(movieId).get());

        return movies;
    }
}
