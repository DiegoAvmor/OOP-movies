package moviewebservice.service;

import moviewebservice.repository.MovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <h>Clase anotada como servicio cuya unica funcion es proveer una conexion a la
 * base de datos.
 */
@Service
public class MovieGenreService {
    @Autowired
    MovieGenreRepository movieGenreRepository;
}
