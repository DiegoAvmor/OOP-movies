package webservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import webservice.model.Rating;
import webservice.util.RatingRaw;

/**
 * Provee un medio de obtención de los ratings.
 *
 * @author diego
 */
@Service
public class RatingService {
    //Constructor vacio
    public RatingService(){}

    //Metodo que consume el api para obtener el rating de una pelicula apartir de su ip
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
        movieRatingRaw= restTemplate.getForObject("http://www.omdbapi.com/?i="+movieRatingRaw.getImdb_id()+"&apikey="+APIKey2, RatingRaw.class);
        rt2= movieRatingRaw.getImdbRating();
        rt3= movieRatingRaw.getMetascore();
        //--------------------------------------------------------------------------------
        Rating movieRating= new Rating(rt1,rt3,rt2);
        movieRating.giveTotalScore();
        return movieRating;
    }
}
