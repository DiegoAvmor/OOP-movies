package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import webservice.controller.PersonalRatingController;
import webservice.model.Account;
import webservice.model.PersonalRating;

import java.util.List;

/**
 * Clase que contiene el constructor visual de la subVentana AccountInfoWindow y metodos necesarios
 * para realizar la parte de edicion de datos del perfil.
 */
@Component
public class AccountInfoWindow extends Window {

    @Autowired
    private PersonalRatingController personalRatingController;

    @Autowired
    private EditPassWindow editPassWindow;

    private VerticalLayout userInfo= new VerticalLayout();
    private HorizontalLayout passInfo= new HorizontalLayout();
    private HorizontalLayout userInfoButtons= new HorizontalLayout();
    private Label password;
    private Button editpass;
    private Label description;
    private Button editInfo;
    private Button confirmInfo;
    private Grid<PersonalRating> grid;

    AccountInfoWindow()
    {
        super();

        editInfo= new Button("Edit");
        editpass= new Button(VaadinIcons.PENCIL);
        confirmInfo= new Button(VaadinIcons.CHECK);
        password= new Label();
        description= new Label();
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


        description.setContentMode(ContentMode.HTML); // Formato con anotaciones HTML
        description.setWidth("500px");

        userInfo.addComponents(description,userInfoButtons,passInfo, grid);

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

    public void loadData(Account account) {
        this.setCaption(account.getUsername());
        description.setValue(String.format("<h2><b>Profile Description</b></h2><p>%s</p>",account.getSummary()));
        if(account.getSummary()==null){description.setValue("<h2><b>Profile Description</b></h2><p></p>" +
                "No user description yet.");}
        editpass.addClickListener(e-> editPassword(account));
        loadPersonalRatings(account);
    }

    public void loadPersonalRatings(Account account) {
        List<PersonalRating> gridFill = personalRatingController.getPersonalRatingsByUsername(account.getUsername());
        grid.setColumns("personalRatingId.id", "rating");
        grid.setItems(gridFill);
    }
}
