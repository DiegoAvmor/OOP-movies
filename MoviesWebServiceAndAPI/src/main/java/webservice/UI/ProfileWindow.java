package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import webservice.controller.AccountController;
import webservice.controller.PersonalRatingController;
import webservice.model.Account;
import webservice.model.PersonalRating;

import java.util.List;

/**
 * Clase que contiene el constructor visual de la subVentana ProfileWindow y metodos necesarios
 * para realizar la parte de edicion de datos del perfil.
 */
@Component
public class ProfileWindow extends Window {

    @Autowired
    AccountController accountController;

    @Autowired
    EditPassWindow editPassWindow;

    @Autowired
    private PersonalRatingController personalRatingController;

    VerticalLayout userInfo;
    HorizontalLayout passInfo;
    HorizontalLayout userInfoButtons;
    TextArea descriptionArea;
    Label password;
    Button editpass;
    Label descriptionTittle;
    Button editInfo;
    Button confirmInfo;
    private Grid<PersonalRating> grid;

    public ProfileWindow(){}

    public void ProfileWindowInit(Account prof)
    {
        setCaption(prof.getUsername());
        //--------Aqui se va a crear los objetos necesarios para mostrar la informacion del Usuario-----------
        userInfo= new VerticalLayout();
        passInfo= new HorizontalLayout();
        userInfoButtons= new HorizontalLayout();
        editInfo= new Button("Edit");
        editpass= new Button(VaadinIcons.PENCIL);
        confirmInfo= new Button(VaadinIcons.CHECK);
        password= new Label();
        descriptionArea= new TextArea();
        descriptionArea.setWordWrap(true);
        descriptionArea.setSizeFull();
        descriptionTittle= new Label();
        grid = new Grid<>(PersonalRating.class);
        grid.setSizeFull();

        password.setValue("Change Password");
        passInfo.addComponents(password,editpass);


        confirmInfo.setStyleName("primary");
        editInfo.setIcon(VaadinIcons.PENCIL);
        editInfo.setStyleName("primary");
        userInfoButtons.addComponents(editInfo,confirmInfo);
        userInfoButtons.setComponentAlignment(editInfo,Alignment.MIDDLE_CENTER);
        userInfoButtons.setComponentAlignment(confirmInfo,Alignment.MIDDLE_CENTER);


        descriptionTittle.setContentMode(ContentMode.HTML); // Formato con anotaciones HTML
        descriptionTittle.setWidth("500px");
        descriptionTittle.setValue("<h2><b>Profile Description</b></h2><p></p>");
        if(prof.getSummary()==null){descriptionArea.setValue("No profile description yet.");}
        else{descriptionArea.setValue(prof.getSummary());}
        descriptionArea.setEnabled(false);

        editpass.addClickListener(e-> editPassword(prof));
        editInfo.addClickListener(e-> descriptionArea.setEnabled(true));
        confirmInfo.addClickListener(e-> updateDescription(prof));

        userInfo.addComponents(descriptionTittle,descriptionArea,userInfoButtons,passInfo, grid);

        loadPersonalRatings(prof);

        //-----Configuracion de la ventana---------
        setResizable(false);
        setModal(true);
        center();
        setHeight("600");
        setWidth("500");
        setClosable(true);
        setDraggable(false);
        setContent(userInfo);
    }

    /**
     * Metodo que abre la ventana para la edicion de la contraseña del usuario
     * @param prof
     */
    public  void editPassword(Account prof)
    {
        editPassWindow.EditPassWindowInit(prof);
        UI.getCurrent().addWindow(editPassWindow);
    }

    /**
     * Metodo cuyo funcion es realizar la actualizacion de la descripcion
     * del usuario en la base de datos.
     * @param prof
     */
    public void updateDescription(Account prof)
    {
        if(descriptionArea.isEnabled()) {
            prof.setSummary(descriptionArea.getValue());
        try{
            prof=accountController.editAccountDescription(prof);
        }
        catch (NullPointerException e){System.out.print("\nError Null");}

        descriptionArea.setEnabled(false);
        MainView main= (MainView) UI.getCurrent().getUI();
        main.setSessionAccount(prof);
        }
    }

    public void loadPersonalRatings(Account account) {
        List<PersonalRating> gridFill = personalRatingController.getPersonalRatingsByUsername(account.getUsername());
        grid.setColumns("personalRatingId.id", "rating");
        grid.setItems(gridFill);
    }
}
