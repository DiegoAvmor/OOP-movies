package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.controller.AccountController;
import webservice.model.Account;
/**
 * Clase que extiende a Window el cual contiene los metodos y constructores para realizar
 * de forma concreta las acciones de la ventana de cambio de contraseña.
 */
@Component
public class EditPassWindow extends Window {

    @Autowired
    private AccountController accountController;

    TextField password;
    TextField passConfirm;
    Button confirmBt;
    VerticalLayout editForm;
    Account profToEdit;

    public EditPassWindow(){}

    /**
     * Metodo que tiene como unico funcionamiento el iniciar los elementos
     * de la ventana
     * @param account
     */
    public void EditPassWindowInit(Account account)
    {
        setCaption("Change Password");
        this.profToEdit= account;

        editForm= new VerticalLayout();
        confirmBt= new Button("Confirm Changes");
        password= new TextField("Password");
        passConfirm= new TextField("Confirm Password");
        //------------------------------------------------------
        confirmBt.setStyleName("primary");
        confirmBt.setIcon(VaadinIcons.CHECK);
        confirmBt.addClickListener(e->checkNewPassword());

        editForm.addComponents(password,passConfirm,confirmBt);
        editForm.setComponentAlignment(password,Alignment.MIDDLE_CENTER);
        editForm.setComponentAlignment(passConfirm,Alignment.MIDDLE_CENTER);
        editForm.setComponentAlignment(confirmBt,Alignment.MIDDLE_CENTER);
        //-----Configuracion de la ventana---------
        setResizable(false);
        setModal(true);
        center();
        setHeight("250");
        setWidth("300");
        setClosable(true);
        setDraggable(false);
        setContent(editForm);
    }

    /**
     * Metodo que verifica si se ingreso de forma correcta la nueva contraseña, en caso de que
     * no lo haya sido este muestra una notficacion.
     */
    public  void checkNewPassword()
    {
        Notification aux=null;
        if(password.getValue().equals(passConfirm.getValue()) &&(password.getValue().length()!=0&&passConfirm.getValue().length()!=0))
        {
            //Actualizo informacion
            this.profToEdit.setPassword(passConfirm.getValue());
            Account ac=accountController.updateAccountPassword(this.profToEdit);
            this.profToEdit= ac;
            MainView main= (MainView) UI.getCurrent().getUI();
            main.setSessionAccount(ac);
            this.close();
        }
        else {
            aux= new Notification("Password Confirmation failed",Notification.TYPE_WARNING_MESSAGE);
            aux.setPosition(Position.BOTTOM_CENTER);
            aux.show(Page.getCurrent());
        }
    }
}
