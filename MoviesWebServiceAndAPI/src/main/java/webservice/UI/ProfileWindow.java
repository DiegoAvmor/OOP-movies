package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import webservice.model.Account;

/**
 * Clase que contiene el constructor visual de la subVentana ProfileWindow y metodos necesarios
 * para realizar la parte de edicion de datos del perfil.
 */
public class ProfileWindow extends Window {

    VerticalLayout userInfo= new VerticalLayout();
    HorizontalLayout passInfo= new HorizontalLayout();
    HorizontalLayout userInfoButtons= new HorizontalLayout();
    Label password;
    Button editpass;
    Label description;
    Button editInfo;
    Button confirmInfo;
    ProfileWindow(Account prof)
    {
        super(prof.getUsername());
        //--------Aqui se va a crear los objetos necesarios para mostrar la informacion del Usuario-----------

        editInfo= new Button("Edit");
        editpass= new Button(VaadinIcons.PENCIL);
        confirmInfo= new Button(VaadinIcons.CHECK);
        password= new Label();
        description= new Label();

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
        description.setValue(String.format("<h2><b>Profile Description</b></h2><p>%s</p>",prof.getSummary()));
        if(prof.getSummary()==null){description.setValue("<h2><b>Profile Description</b></h2><p></p>"+"No user description yet.");}

        editpass.addClickListener(e-> editPassword(prof));

        userInfo.addComponents(description,userInfoButtons,passInfo);

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
        EditPassWindow infoWindow= new EditPassWindow(prof);
        UI.getCurrent().addWindow(infoWindow);
    }
}
