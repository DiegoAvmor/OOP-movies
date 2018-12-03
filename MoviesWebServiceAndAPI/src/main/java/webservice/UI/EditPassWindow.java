package webservice.UI;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import webservice.controller.AccountController;
import webservice.model.Account;

public class EditPassWindow extends Window {

    @Autowired
    private AccountController accountController;

    TextField username;
    TextField password;
    TextField passConfirm;
    Button confirmBt;
    VerticalLayout editForm;
    Account profToEdit;

    public EditPassWindow(Account prof)
    {
        super("Change Password");
        this.profToEdit= prof;

        editForm= new VerticalLayout();
        confirmBt= new Button("Confirm Changes");
        //username= new TextField("Username");
        password= new TextField("Password");
        passConfirm= new TextField("Confirm Password");
        //------------------------------------------------------
        //username.setValue(prof.getUsername());
        confirmBt.setStyleName("primary");
        confirmBt.setIcon(VaadinIcons.CHECK);
        confirmBt.addClickListener(e->checkNewPassword());

        editForm.addComponents(/*username,*/password,passConfirm,confirmBt);
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
            //Actualizo informacion, falla al intentarlo
            //accountController.updateAccount(this.profToEdit);
        }
        else {
            aux= new Notification("Password Confirmation failed",Notification.TYPE_WARNING_MESSAGE);
            aux.setPosition(Position.BOTTOM_CENTER);
            aux.show(Page.getCurrent());
        }
    }
}
