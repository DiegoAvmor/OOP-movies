package webservice.UI;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import webservice.model.Movie;

/**
 * <h>Clase que extiende a Imagen que tiene un constructor para darle forma
 * al poster de la pelicula correspondiente.
 */
public class MoviePoster extends Image {

    private Movie movie;

    public MoviePoster(Movie movie)
    {
        super();
        this.movie = movie;
        setCaption(this.movie.getTitle());//Titulo
        setSource(new ExternalResource(this.movie.getPoster_path()));//Imagen
        //------------Dimensiones del Poster----------------------------------
        setHeight(300,Unit.PIXELS);
        setWidth(200, Unit.PIXELS);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
