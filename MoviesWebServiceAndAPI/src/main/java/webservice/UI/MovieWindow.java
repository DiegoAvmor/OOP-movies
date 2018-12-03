package webservice.UI;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import webservice.model.Rating;

/**
 * <h>Clase que extiende a Window que tiene como accion principal
 * crear la subventana para la pelicula.
 */
public class MovieWindow extends Window {

    private final HorizontalLayout horizontalLayout;
    private final VerticalLayout infoLayout;
    private final CssLayout personalRatingLayout;
    private final Label overview;
    private final Grid<Rating> ratings;
    private final Rating rating;
    private final Image poster;
    private final Label personalRatingHeader;
    private final TextField personalRating;
    private final Button rateButton;
    private final Label ratingsHeader;

    public MovieWindow(MoviePoster moviePoster)
    {
        super(moviePoster.getMovie().getTitle(), moviePoster);
        MainView main= (MainView) UI.getCurrent().getUI();
        // Inicialización de los atributos
        horizontalLayout = new HorizontalLayout();
        personalRatingLayout = new CssLayout();
        infoLayout = new VerticalLayout();
        rating = moviePoster.getMovie().getRating();
        personalRating = new TextField();
        rateButton = new Button("Rate");
        ratingsHeader = new Label();
        personalRatingHeader = new Label();
        overview = new Label();
        ratings = new Grid<>(Rating.class);
        poster = moviePoster;

        personalRatingHeader.setContentMode(ContentMode.HTML);
        ratingsHeader.setContentMode(ContentMode.HTML);

        personalRatingHeader.setValue("<h3>Your rating</h3>");
        ratingsHeader.setValue("<h2><b>Ratings from external sources</b></h2>");

        // Añadir componentes a los layouts
        horizontalLayout.addComponents(poster, infoLayout);
        infoLayout.addComponents(overview, personalRatingLayout, ratings);
        personalRatingLayout.addComponents(personalRating, rateButton);
        if(main.sessionAccount!=null)
        {
            personalRatingLayout.setEnabled(true);
        }
        else {personalRatingLayout.setEnabled(false);}
        personalRatingLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

    	// Tamaño del poster
        poster.setWidth("300px");
        poster.setHeight("450px");

        // Colocar ratings en el grid
        ratings.setItems(rating);
        ratings.setHeight("77px");

    	// Colocar sinopsis de la película con formato
        overview.setContentMode(ContentMode.HTML); // Formato con anotaciones HTML
    	overview.setWidth("500px");
    	overview.setValue(String.format("<h2><b>Overview</b></h2><p>%s</p>",
                moviePoster.getMovie().getOverview()));

        // Configuración de la ventana
        setResizable(false);
        setModal(true);
        center();
        setHeight("486px");
        setWidth("950px");
        setClosable(true);
        setDraggable(false);
        setContent(horizontalLayout);
    }
}
