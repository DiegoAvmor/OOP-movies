package moviewebservice.controller;

import moviewebservice.model.Genre;
import moviewebservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api.v1")
@CacheConfig(cacheNames= {"genres"})
public class GenreController {
    @Autowired
    GenreService genreService;

    /**
     * <p>Metodo mapeado al metodo HTTP GET en la url "/genres" el cual devolvera el listado
     * completo de los generos que estan disponibles en la base de datos.
     * @return Devuele una List de tipo Genre.
     */
    @Cacheable
    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }
    
    /**
     * Metodo mapeado al metodo HTTP GET en la url "/genres/{id}" el cual devuelve el genero 
     * mediante el uso de su identificador.
     * @param id int que representa el identificador del genero.
     * @return Devuelve un Objeto tipo Genre.
     */
    @Cacheable(key="#id")
    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable int id)
    {
        return genreService.getGenreById(id);
    }
}
