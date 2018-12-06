package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.controller.AccountController;
import webservice.controller.PersonalRatingController;
import webservice.model.Account;
import webservice.model.PersonalRating;

import java.util.List;

/**
 * Clase que contiene el constructor visual de la subVentana AccountWindow y metodos necesarios
 * para realizar la parte de edicion de datos del perfil.
 */
@Component
public class AccountWindow extends Window {

    @Autowired
    private AccountController accountController;

    @Autowired
    private EditPassWindow editPassWindow;

    @Autowired
    private PersonalRatingController personalRatingController;

    private VerticalLayout accountInfo;
    private HorizontalLayout passInfo;
    private HorizontalLayout accountInfoButtons;
    private TextArea descriptionArea;
    private Label password;
    private Button editpass;
    private Label descriptionTittle;
    private Button editInfo;
    private Button confirmInfo;
    private Grid<PersonalRating> grid;
    private Label credentialsLabel;
    private Label personalRatingsLabel;


    public AccountWindow(){}
    
    /**
    * Metodo que tiene como funcion unica la inicializacion de los elementos de la ventana
    * del perfil del usuario.
    */
    public void ProfileWindowInit(Account account)
    {
        setCaption(account.getUsername());
        //--------Aqui se va a crear los objetos necesarios para mostrar la informacion del Usuario-----------
        accountInfo = new VerticalLayout();
        passInfo= new HorizontalLayout();
        accountInfoButtons = new HorizontalLayout();
        editInfo= new Button("Edit");
        editpass= new Button(VaadinIcons.PENCIL);
        confirmInfo= new Button(VaadinIcons.CHECK);
        password= new Label();
        descriptionArea= new TextArea();
        descriptionArea.setWordWrap(true);
        descriptionArea.setSizeFull();
        descriptionTittle= new Label();
        credentialsLabel = new Label("<h3><b>Change credentials</b></h3>", ContentMode.HTML);
        personalRatingsLabel = new Label("<h3><b>Account ratings</b></h3>", ContentMode.HTML);
        grid = new Grid<>(PersonalRating.class);

        grid.setSizeFull();
        grid.setHeight("150px");


        password.setValue("Change Password");
        passInfo.addComponents(password,editpass);


        confirmInfo.setStyleName("primary");
        editInfo.setIcon(VaadinIcons.PENCIL);
        editInfo.setStyleName("primary");
        accountInfoButtons.addComponents(editInfo,confirmInfo);
        accountInfoButtons.setComponentAlignment(editInfo,Alignment.MIDDLE_CENTER);
        accountInfoButtons.setComponentAlignment(confirmInfo,Alignment.MIDDLE_CENTER);


        descriptionTittle.setContentMode(ContentMode.HTML); // Formato con anotaciones HTML
        descriptionTittle.setWidth("500px");
        descriptionTittle.setValue("<h3><b>Account Description</b></h3>");
        if(account.getSummary()==null){descriptionArea.setValue("No account description yet.");}
        else{descriptionArea.setValue(account.getSummary());}
        descriptionArea.setEnabled(false);

        descriptionArea.setHeight("50px");

        editpass.addClickListener(e-> editPassword(account));
        editInfo.addClickListener(e-> descriptionArea.setEnabled(true));
        confirmInfo.addClickListener(e-> updateDescription(account));

        accountInfo.addComponents(descriptionTittle,descriptionArea, accountInfoButtons,
                credentialsLabel, passInfo, personalRatingsLabel, grid);

        loadPersonalRatings(account);

        //-----Configuracion de la ventana---------
        setResizable(false);
        setModal(true);
        center();
        setHeight("610");
        setWidth("500");
        setClosable(true);
        setDraggable(false);
        setContent(accountInfo);
    }

    /**
     * Metodo que abre la ventana para la edicion de la contraseña del usuario
     * @param account
     */
    public  void editPassword(Account account)
    {
        editPassWindow.EditPassWindowInit(account);
        UI.getCurrent().addWindow(editPassWindow);
    }

    /**
     * Metodo cuyo funcion es realizar la actualizacion de la descripcion
     * del usuario en la base de datos.
     * @param account
     */
    public void updateDescription(Account account)
    {
        if(descriptionArea.isEnabled()) {
            account.setSummary(descriptionArea.getValue());
        try{
            account=accountController.editAccountDescription(account);
        }
        catch (NullPointerException e){System.out.print("\nError Null");}

        descriptionArea.setEnabled(false);
        MainView main= (MainView) UI.getCurrent().getUI();
        main.setSessionAccount(account);
        }
    }
    /**
    * Metodo cuya funcion es realizar la carga de los ratings personales de su respectivo
    * usuario.
    * @param account
    */
    public void loadPersonalRatings(Account account) {
        List<PersonalRating> gridFill = personalRatingController.getPersonalRatingsByUsername(account.getUsername());
        grid.setColumns("personalRatingId.id", "rating");
        grid.setItems(gridFill);
    }
}
