package webservice.model;

/**
 *  Clase modelo para del rating de las peliculas.
 */
public class Rating {
    //--------TheMovieDB-------------
    private float TMDb =0; //calificacion de TheMovieDB float 0-10
    //--------OMDB------------------
    private float Metacritic =0; // 0-100
    private float IMDb =0; //0-10(int/float)
    //----------General-----------------
    private float averageScore = 0;

    public Rating(){};

    public Rating(float TMDb, float Metacritic, float IMDb)
    {
        this.TMDb = TMDb;
        this.Metacritic = Metacritic;
        this.IMDb = IMDb;
    }
    
     /**
    * Metodo cuya funcion unica es la obtencion del promedio general de la pelicula
    * correspondiente.
    */
    public void giveTotalScore()
    {
        if(this.Metacritic !=0){
        this.averageScore = (((Metacritic *10)/100)+this.TMDb +this.IMDb)/3;}
        else this.averageScore =(this.TMDb +this.IMDb)/2;
    }
    /**
     * @return the TMDb
     */
    public float getTMDb() {
        return TMDb;
    }

    /**
     * @param TMDb the TMDb to set
     */
    public void setTMDb(float TMDb) {
        this.TMDb = TMDb;
    }

    /**
     * @return the Metacritic
     */
    public float getMetacritic() {
        return Metacritic;
    }

    /**
     * @param Metascore the Metacritic to set
     */
    public void setMetacritic(int Metascore) {
        this.Metacritic = Metascore;
    }

    /**
     * @return the IMDb
     */
    public float getIMDb() {
        return IMDb;
    }

    /**
     * @param IMDb the IMDb to set
     */
    public void setIMDb(float IMDb) {
        this.IMDb = IMDb;
    }

    /**
     * @return the averageScore
     */
    public float getAverageScore() {
        return averageScore;
    }

    /**
     * @param averageScore the averageScore to set
     */
    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
    @Override
    public String toString()
    {
        return "TheMovieDB: "+ this.getTMDb()+"\n"+
                "Imd: "+ this.getIMDb()+"\n"+
                "Metacritic: "+ this.getMetacritic()+"\n"+
                "TotalScore: "+ this.getAverageScore();
    }
}
