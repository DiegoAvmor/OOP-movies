package webservice.UI;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.controller.PersonalRatingController;
import webservice.model.PersonalRating;
import webservice.model.Rating;
import webservice.util.PersonalRatingId;

/**
 * <h>Clase que extiende a Window que tiene como accion principal
 * crear la subventana para la pelicula.
 */
@Component
public class MovieWindow extends Window {

    @Autowired
    private PersonalRatingController personalRatingController;

    private final HorizontalLayout horizontalLayout;
    private final VerticalLayout infoLayout;
    private final CssLayout interactiveRatingLayout;
    private final HorizontalLayout personalRatingLayout;
    private final Label overview;
    private final Grid<Rating> ratings;
    private Rating rating;
    private MoviePoster moviePoster;
    private TextField personalRating;
    private final Button rateButton;
    private final Label label;

    public MovieWindow()
    {
        super();
        horizontalLayout = new HorizontalLayout();
        interactiveRatingLayout = new CssLayout();
        infoLayout = new VerticalLayout();
        personalRating = new TextField();
        rateButton = new Button("Rate");
        overview = new Label();
        ratings = new Grid<>(Rating.class);
        personalRatingLayout = new HorizontalLayout();
        label = new Label();

        // Configuración de la ventana
        setResizable(false);
        setModal(true);
        center();
        setHeight("486px");
        setWidth("950px");
        setClosable(true);
        setDraggable(false);
    }
    
    /**
    * Metodo que tiene como funcion realizar el alamacenamiento del rating ingresado
    * por el usuario.
    */
    private void rate() {
        MainView mainView = (MainView) UI.getCurrent().getUI();
        PersonalRatingId id = new PersonalRatingId(moviePoster.getMovie().getId(),
                mainView.getSessionAccount().getUsername());
        personalRatingController.loadRating(new PersonalRating(id,
                Integer.parseInt(personalRating.getValue())));

        label.setValue("Rating stored");
    }
    
    /**
    * Metodo que tiene como funcion de realizar la carga de los elementos
    * en la ventana de la pelicula.
    */
    public void loadData(MoviePoster moviePoster) {

        this.moviePoster = moviePoster;

        // Tamaño del moviePoster
        this.moviePoster.setWidth("300px");
        this.moviePoster.setHeight("450px");

        this.setCaption(moviePoster.getMovie().getTitle());
        rating = moviePoster.getMovie().getRating();

        // Colocar ratings en el grid
        ratings.setItems(rating);
        ratings.setHeight("77px");

        overview.setValue(String.format("<h2><b>Overview</b></h2><p>%s</p>",
                moviePoster.getMovie().getOverview()));

        MainView main= (MainView) UI.getCurrent().getUI();

        if(main.getSessionAccount() !=null)
            interactiveRatingLayout.setEnabled(true);
        else
            interactiveRatingLayout.setEnabled(false);

        horizontalLayout.removeAllComponents();

        // Añadir componentes a los layouts
        horizontalLayout.addComponents(moviePoster, infoLayout);
        infoLayout.addComponents(overview, personalRatingLayout, ratings);
        personalRatingLayout.addComponents(interactiveRatingLayout, label);
        interactiveRatingLayout.addComponents(personalRating, rateButton);

        rateButton.addListener(e -> rate());

        interactiveRatingLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        // Colocar sinopsis de la película con formato
        overview.setContentMode(ContentMode.HTML); // Formato con anotaciones HTML
        overview.setWidth("500px");

        label.setValue(findPersonalRating());

        setContent(horizontalLayout);
    }
    
    /**
    * Metodo que tiene como funcion unica hacer la carga del rating del usuario en la
    * pelicula correspondiente que se haya seleccionado.
    */
    private String findPersonalRating() {
        MainView main = (MainView) UI.getCurrent().getUI();
        String prompt = "Your rating: ";

        if(main.getSessionAccount() != null) {
            PersonalRatingId id = new PersonalRatingId(moviePoster.getMovie().getId(),
                    main.getSessionAccount().getUsername());
            if(personalRatingController.existsById(id))
                prompt += Integer.toString(personalRatingController.
                        getPersonalRatingById(id).getRating());
            else
                prompt = "Not rated yet";
        }
        else
            prompt = "Login to rate";

        return prompt;
    }
}
