package webservice.UI;


import com.vaadin.ui.Window;
import webservice.model.Account;

/**
 * Clase que contiene el constructor visual de la subVentana ProfileWindow y metodos necesarios
 * para realizar la parte de edicion de datos del perfil.
 */
public class ProfileWindow extends Window {

    ProfileWindow(Account prof)
    {
        super(prof.getUsername());
        //--------Aqui se va a crear los objetos necesarios para mostrar la informacion del Usuario-----------



        //-----Configuracion de la ventana---------
        setResizable(false);
        setModal(true);
        center();
        setHeight("600");
        setWidth("500");
        setClosable(true);
        setDraggable(false);
    }
}
