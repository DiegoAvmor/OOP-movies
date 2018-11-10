package MovieRating.Service.service;

import MovieRating.Service.exceptions.MovieNotFoundException;
import MovieRating.Service.model.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<h>Clase que se encarga de realizar las acciones correspondientes para las peliculas</h>
 * @author diego
 */
public class MovieService {
    
    private static Map<Integer,Movie> movielist= new HashMap<Integer,Movie>(); 
    
    
    public MovieService(){
        //El constructor hay que dejarlo, pero esto de movielist.put no sera necesario despues
        //Esto ya no sera necesario para cuando establezcas la base de datos asi que lo puedes borrar cuando ya lo tengas
        movielist.put(1, new Movie("El Matador","Hombre que tiene que ver con un matador","Horror",19239));
        movielist.put(2, new Movie("El Grinch","Personas felices, yo enojado","Familiar",992811));
        movielist.put(3, new Movie("AmericanPie","No es acerca de un pie","Horror",1928192));
        movielist.put(4, new Movie("Lilo&Stitch","Snitches get Stitches","Animation",473617));
        movielist.put(5, new Movie("I.T.","Payaso trites, niños desaparecidos","Science-Fiction",8129129));
        
    }
    /**
     * <p>Metodo que recibe como para metro el id de la pelicula, realiza la busqueda
     * y devuelve dicha pelicula. Tira una excepcion en caso de que el id de la pelicula
     * no exista.
     * @param id
     * @return Devuelve un Objeto tipo Movie
     */
    public Movie getMovieFromId(int id)
    {
        //Aqui se conecta a la base de datos y debe de obetner la pelicula apartir del id
        //MovieIdSearchInDb(id); Este es un nombre no importante ya despues tu sabras como ponerle o que cambios hacerle a esta parte
        Movie movie= movielist.get(id);
        if(movie==null)
        {
            throw new MovieNotFoundException("Movie id: "+ id+ " not found");
        }
      return movie;
    }
    
    /**
     * <p>Metodo que devuelve una lista de objetos "Movie" haciendo uso de
     * Paginacion, en otras palabras regresa una lista de objetos "Movie" de una seccion apartir
     * de los parametros start y size.
     * @param Start parametro donde indica en que parte de la lista empieza
     * @param Size paramtro que indica el numero de peliculas que se mostrara
     * @return Devuelve una List<Movie>
     */
    public List<Movie> getAllMoviesForPagination(int Start, int Size)
    {
        ArrayList<Movie> list= new ArrayList<Movie>(movielist.values());
        if(Size+Start>movielist.size()) {return new ArrayList<Movie>();}
        return list.subList(Start, Start+Size);
    }
    /**
     * <p>Metodo que devuelve la lista completa de peliculas.
     * @return Devuelve una List<Movie> 
     */
    public List<Movie> getAllMovies()
    {
        return new ArrayList<Movie>(movielist.values());
    }
}
