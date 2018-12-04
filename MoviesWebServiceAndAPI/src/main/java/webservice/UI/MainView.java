
package webservice.UI;

import com.vaadin.event.MouseEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import webservice.controller.MovieController;
import webservice.model.Movie;
import webservice.model.Account;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@SpringUI(path="")
public class MainView extends UI {

    @Autowired
    private MovieWindow movieWindow;

    @Autowired
    private MovieController movieController;

    @Autowired
    private Login loginWindow;

    @Autowired
    private AccountCreation accountCreationWindow;

    @Autowired
    private ProfileWindow profileWindow;

    private Account sessionAccount = null;

    private int columns = 5;

    // Componentes del UI
    private TextField searchBar= new TextField();
    private Button loginButton = new Button("Login");
    private Button clearFilterButton = new Button();
    private MenuBar header;
    private List<Movie> movieList;
    private MouseEvents.ClickListener showPopup;
    private ArrayList<MoviePoster> moviePosters = new ArrayList<>();
    private Button searchButton = new Button();
    private GridLayout moviesLayout = new GridLayout(columns, 1);
    private VerticalLayout parentLayout = new VerticalLayout();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private CssLayout filtering = new CssLayout();
    private Button accountButton = new Button("Create account");

    @Override
    protected void init(VaadinRequest request) {
        parentLayout.addComponents(toolbar, moviesLayout);

        toolbar.setSizeFull();
        moviesLayout.setSizeFull();

        toolbar.addComponents(filtering, accountButton, loginButton);
        searchButton.setIcon(VaadinIcons.FILTER);
        clearFilterButton.setIcon(VaadinIcons.CLOSE_SMALL);
        searchBar.setPlaceholder("search by title...");
        searchButton.addListener(e -> updateList());
        clearFilterButton.addListener(e -> clearFilter());
        loginButton.addListener(e -> this.showLoginWindow());
        accountButton.addListener(e -> this.showAccountCreationWindow());

        accountButton.setWidth(null);

        toolbar.setComponentAlignment(loginButton, Alignment.MIDDLE_RIGHT);
        toolbar.setComponentAlignment(accountButton, Alignment.MIDDLE_RIGHT);

        filtering.addComponents(searchBar, clearFilterButton, searchButton);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        setContent(parentLayout);
    }

    private void showAccountCreationWindow() {
        UI.getCurrent().addWindow(accountCreationWindow);
    }

    /**
     * Metodo que instancia una subVentana para el inicio de sesion
     */
    public void showLoginWindow()
    {
        UI.getCurrent().addWindow(loginWindow);
    }

    private void clearFilter() {
        searchBar.clear();
        updateList();
    }

    /**
     * Metodo que instancia una subVentana con los datos de la
     * pelicula seleccionada.
     * @param poster
     */
    public void showMovieWindow(MoviePoster poster){
        MoviePoster moviePoster = new MoviePoster(poster.getMovie());
        moviePoster.setCaption(null);

        movieWindow.loadData(moviePoster);

        UI.getCurrent().addWindow(movieWindow);
    }

    /**
     * Metodo para la obtencion de todas la peliculas.
     */
    public  void getMovies()
    {
        try{
            movieList = movieController.getAllMovies();

            if(movieList == null) {
                System.out.println("movieList is null");
                System.exit(-1);
            }
        }
        catch(NullPointerException ll){System.out.print("No Movie available in the database");}
    }

    /**
     * Metodo que se encarga de cargar en la UI los posters de las peliculas, ademas se le agrega
     * a cada poster un evento el cual al ser oprimidos este abre en la pantalla actual una subventana
     * con la informacion de la pelicula seleccionada previamente.
     */

    public void loadPosters() {
        for(Movie movie : movieList) {
            MoviePoster moviePoster = new MoviePoster(movie);
            moviePosters.add(moviePoster);
            moviesLayout.addComponent(moviePoster);
        }

        // Evento para el popup
        for(MoviePoster moviePoster: moviePosters) {
            showPopup = e -> showMovieWindow(moviePoster);
            moviePoster.addClickListener(showPopup);
        }
    }

    @PostConstruct
    public void loadStartPosters() {
        getMovies();
        loadPosters();
    }

    /**
     * Metodo que cambia la vista de forma que toma en cuenta de que se a
     * iniciado una session.
     * @param prof
     */
    public void changeTopLayout(Account prof)
    {
        this.sessionAccount = prof;
        //this.accountController=accountController;
        toolbar.removeComponent(loginButton);
        toolbar.removeComponent(accountButton);
        //-----------------MenuBar----------------
        header= new MenuBar();
        header.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        toolbar.addComponent(header);
        toolbar.setComponentAlignment(header,Alignment.MIDDLE_LEFT);
        MenuBar.MenuItem profile= header.addItem(sessionAccount.getUsername(),null);
        //------Se agregan los items en el subMenu-----------
        MenuBar.MenuItem Infor= profile.addItem("UserInfo", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                profileWindow();//Evento que se realiza cuando se aprieta el item UserInfo
            }
        });
        MenuBar.MenuItem logout= profile.addItem("Log Out", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                loginEnable();//Evento que se realiza cuanod se aprieta el item Log-Out
            }
        });
    }

    /**
     * Metodo que se encarga de mostrar la ventana del perfil del usuario
     * registrado en la sesion actual.
     */
    public  void profileWindow()
    {
        profileWindow.ProfileWindowInit(sessionAccount);
        UI.getCurrent().addWindow(profileWindow);
    }

    /**
     * Metodo que se encarga de eliminar el submenu y volver a meter el boton LogIn.
     */
    public void loginEnable()
    {
        toolbar.removeComponent(header);
        sessionAccount =null; //Se vuelve nula el perfil de la sesion actual.
        header=null;//Se vuelve nulo el submenu
        toolbar.addComponents(accountButton,loginButton);
        toolbar.setComponentAlignment(accountButton,Alignment.MIDDLE_RIGHT);
        toolbar.setComponentAlignment(loginButton,Alignment.MIDDLE_RIGHT);
    }

    /**
     * Metodo que se encarga de hacer el actualizado de peliculas, hace el uso del
     * search bar para mostrar las peliculas que tienen relacion con lo que se ha introducido
     */
    public void updateList() {
        movieList = movieController.getAllMovies(searchBar.getValue());

        System.out.println("\nSearch bar value = " + "'" + searchBar.getValue() + "'");
        System.out.println("Matches");
        for(Movie movie : movieList)
            System.out.println("   -> " + movie.getTitle());
        System.out.println();

        moviesLayout.removeAllComponents();
        moviePosters.clear();

        loadPosters();
    }

    public Account getSessionAccount() {
        return sessionAccount;
    }

    public void setSessionAccount(Account sessionAccount) {
        this.sessionAccount = sessionAccount;
    }
}

