package moviewebservice.service;

import moviewebservice.model.Rating;
import moviewebservice.util.RatingRaw;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *<h> Clase anotada como servicio el cual su unica funcion es la parte del consumo
 * de un servicio tercero para devolver los ratings de una pelicula.
 */
@Service
public class RatingService {
    //Constructor vacio
    public RatingService(){}

    /**
     * <p> Metodo que recibe como parametro el identificador de la pelicula y llama a un sevicio tercero
     * para realizar la obtencion de los rating de dicha pelicula.
     * @param movie_id int que representa el identificador de la pelicula
     * @return Devuelve un Objeto tipo Rating
     */
    public Rating getRatingByMovieId(int movie_id)
    {
        float rt1,rt2,rt3;
        RatingRaw movieRatingRaw= new RatingRaw();
        RestTemplate restTemplate = new RestTemplate();
        //------------------------------------------------------------------------------------------------------------------------------------------------
        String APIKey = "272f8904aff5923c030b53292132aec4";
        movieRatingRaw= restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movie_id+"?api_key="+APIKey+"&language=en-US", RatingRaw.class);
        rt1=movieRatingRaw.getVote_average();
        //------------------------------------------------------------------------------------------------------------------------------------------------
        String APIKey2= "cfbab405";
        movieRatingRaw= restTemplate.getForObject("http://www.omdbapi.com/?i="+movieRatingRaw.getImdb_id()+"&apikey="+APIKey2,RatingRaw.class);
        rt2= movieRatingRaw.getImdbRating();
        rt3= movieRatingRaw.getMetascore();
        //--------------------------------------------------------------------------------
        Rating movieRating= new Rating(rt1,rt3,rt2);
        System.out.print(movieRating.toString());
        return movieRating;
    }
}
