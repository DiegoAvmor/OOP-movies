package MovieRating.Service.model;

public class Movie {
    private String movieTittle;
    private String description;
    private String genre;
    private long id;
//NOTA: Creo que estos eran los atributos de este modelo que hemos establecido previamente, si falta alguno agregalo
    
    public Movie(){}
    public Movie(String movieTittle, String description, String genre, long id)
    {
        this.movieTittle= movieTittle;
        this.description= description;
        this.genre= genre;
        this.id= id;
    }
    /**
     * @return the movieTittle
     */
    public String getMovieTittle() {
        return movieTittle;
    }

    /**
     * @param movieTittle the movieTittle to set
     */
    public void setMovieTittle(String movieTittle) {
        this.movieTittle = movieTittle;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
