package webservice.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author diego
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingRaw {
    //--------TheMovieDB-------------
    private float vote_average=0; //calificacion de TheMovieDB float 0-10
    private String imdb_id="";
    //--------OMDB------------------
    private float metaScore=0; // 0-100
    private float imdbRating=0; //0-10(int/float)

    @JsonCreator
    public RatingRaw(@JsonProperty("vote_average") float vote_average,
                     @JsonProperty("Metascore") String metaScore,
                     @JsonProperty("imdbRating") String imdbRating) {

        this.vote_average = vote_average;

        if(metaScore != null)
            if(!metaScore.equals("N/A"))
                this.metaScore = Float.parseFloat(metaScore);

        if(imdbRating != null)
            if(!imdbRating.equals("N/A"))
                this.imdbRating = Float.parseFloat(imdbRating);

        /*
        if(metaScore != null & imdbRating != null) {
            if(!metaScore.equals("N/A") & !imdbRating.equals("N/A")) {
                this.metaScore = Float.parseFloat(metaScore);
                this.imdbRating = Float.parseFloat(imdbRating);
            }
        }*/
    }


    public RatingRaw(){};
    /**
     * @return the vote_average
     */
    public float getVote_average() {
        return vote_average;
    }

    /**
     * @param vote_average the vote_average to set
     */
    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    /**
     * @return the imdb_id
     */
    public String getImdb_id() {
        return imdb_id;
    }

    /**
     * @param imdb_id the imdb_id to set
     */
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    /**
     * @return the Metascore
     */
    public float getMetascore() {
        return metaScore;
    }

    /**
     * @param Metascore the Metascore to set
     */
    public void setMetascore(int Metascore) {
        this.metaScore = Metascore;
    }

    /**
     * @return the imdbRating
     */
    public float getImdbRating() {
        return imdbRating;
    }

    /**
     * @param imdbRating the imdbRating to set
     */
    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }
}
