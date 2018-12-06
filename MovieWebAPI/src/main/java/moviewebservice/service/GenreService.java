package moviewebservice.service;

import com.jayway.jsonpath.TypeRef;
import moviewebservice.model.Genre;
import moviewebservice.repository.GenreRepository;
import moviewebservice.util.JsonPathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * <h> Clase anotada como servicio el cual realizara la parte de los servicios
 * entorno a los generos de las peliculas.
 */
@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;
    
    /**
     * <p>Metodo que se ejecuta despues de la construccion de la aplicacion.
     * Verifica la existencia de registros en su tabla correspondiente, en caso de que no haya
     * registros este llama a un servicio tercero para obtener los datos y ya poder llenar la tabla 
     * con lo registros.
     */
    @PostConstruct
    public void loadData() {
        if(genreRepository.findAll().size() == 0) {
            try {
                String APIKey = "272f8904aff5923c030b53292132aec4";

                URL jsonURL = new URL("https://api.themoviedb.org/3/genre/movie/list?api_key=" +
                        APIKey + "&language=en-US");

                List<Genre> genres = JsonPathUtil.getMappedEntities(jsonURL, "$.genres",
                        new TypeRef<List<Genre>>(){});

                genreRepository.saveAll(genres);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * <p> Metodo que devuelve la lista completa de generos almacenadas en la
     * base de datos.
     * @return Devuelve una lista de tipo Genre con los generos de la base de datos.
     */
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
    
    /**
     * <p> Metodo que devuel un objeto tipo genero, se conecta a la base de datos para
     * obtener el genero apartir de su identificador.
     * @param genre_id int que representa el identificador del genero.
     * @return Devuelve un Objeto tipo Genre.
     */
    public Genre getGenreById(Integer genre_id) {
        return genreRepository.findById(genre_id).get();
    }
}
