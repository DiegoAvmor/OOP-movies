package moviewebservice.model;

/**
 *<h>Clase que contiene unicamente los atributos que representan ratings obtenidos
 * por el uso de servicios terceros y un contructor que asigna el promedio de entre
 * los ratings obtenidos.
 */
public class Rating {
    //--------TheMovieDB-------------
    private float TheMovieDB=0; //calificacion de TheMovieDB float 0-10
    //--------OMDB------------------
    private float MetaScore=0; // 0-100
    private float Imd=0; //0-10(int/float)
    //----------General-----------------
    private float AvarageScore=0;

    public Rating(){};
    
    public Rating(float vote_average,float Metascore, float imdbRating)
    {
        this.TheMovieDB= vote_average;
        this.MetaScore= Metascore;
        this.Imd= imdbRating;
        //En muchos casos no hay un rating existente ne Metascore, por lo tanto debemos realizar casos
        if(Metascore!=0)
        {
          this.AvarageScore= (((Metascore*10)/100)+this.TheMovieDB+this.Imd)/3;   
        }
        else{this.AvarageScore= (this.TheMovieDB + this.Imd)/2;}
    }

    /**
     * @return the TheMovieDB
     */
    public float getTheMovieDB() {
        return TheMovieDB;
    }

    /**
     * @param TheMovieDB the TheMovieDB to set
     */
    public void setTheMovieDB(float TheMovieDB) {
        this.TheMovieDB = TheMovieDB;
    }

    /**
     * @return the MetaScore
     */
    public float getMetaScore() {
        return MetaScore;
    }

    /**
     * @param MetaScore the MetaScore to set
     */
    public void setMetaScore(float MetaScore) {
        this.MetaScore = MetaScore;
    }

    /**
     * @return the Imd
     */
    public float getImd() {
        return Imd;
    }

    /**
     * @param Imd the Imd to set
     */
    public void setImd(float Imd) {
        this.Imd = Imd;
    }

    /**
     * @return the AvarageScore
     */
    public float getAvarageScore() {
        return AvarageScore;
    }

    /**
     * @param AvarageScore the AvarageScore to set
     */
    public void setAvarageScore(float AvarageScore) {
        this.AvarageScore = AvarageScore;
    }
    @Override
    public String toString()
    {
        return "TheMovieDB: "+ this.getTheMovieDB()+"\n"+
                "Imd: "+ this.getImd()+"\n"+
                "Metacritic: "+ this.getMetaScore()+"\n"+
                "TotalScore: "+ this.getAvarageScore();
    }
}
