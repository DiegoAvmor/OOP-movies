package moviewebservice.model;

/**
 *
 * @author diego
 */
public class Rating {
    //--------TheMovieDB-------------
    private float vote_average=0; //calificacion de TheMovieDB float 0-10
    //--------OMDB------------------
    private float Metascore=0; // 0-100
    private float imdbRating=0; //0-10(int/float)
    //----------General-----------------
    private float totalScore=0;

    public Rating(){};

    public Rating(float vote_average,float Metascore, float imdbRating)
    {
        this.vote_average= vote_average;
        this.Metascore= Metascore;
        this.imdbRating= imdbRating;
    }

    public void giveTotalScore()
    {
        this.totalScore= (((Metascore*10)/100)+this.vote_average+this.imdbRating)/3;
    }
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
     * @return the Metascore
     */
    public float getMetascore() {
        return Metascore;
    }

    /**
     * @param Metascore the Metascore to set
     */
    public void setMetascore(int Metascore) {
        this.Metascore = Metascore;
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

    /**
     * @return the totalScore
     */
    public float getTotalScore() {
        return totalScore;
    }

    /**
     * @param totalScore the totalScore to set
     */
    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
    @Override
    public String toString()
    {
        return "TheMovieDB: "+ this.getVote_average()+"\n"+
                "Imd: "+ this.getImdbRating()+"\n"+
                "Metacritic: "+ this.getMetascore()+"\n"+
                "TotalScore: "+ this.getTotalScore();
    }
}
