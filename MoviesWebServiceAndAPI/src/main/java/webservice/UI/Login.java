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
 * de forma concreta las acciones a realizar de la ventana Log-In.
 */
@Component
public class Login extends Window {

    @Autowired
    private AccountController accountController;

    private Account account;
    private TextField username;
    private PasswordField password;
    private Button btnLogin;

    public Login()
    {
        super("Login to existing account");
        VerticalLayout vrt= new VerticalLayout();
        //---------Configuracion UI del TextField---------------
        username= new TextField();
        username.setCaption("Username");
        username.setIcon(VaadinIcons.USER);

        //---------Configuracion UI del PasswordField-----------
        password= new PasswordField();
        password.setCaption("Password");
        password.setIcon(VaadinIcons.KEY);

        //---------Configuracion UI del Boton---------------
        btnLogin= new Button("Login",VaadinIcons.CHECK);
        btnLogin.addStyleName("primary");
        //
        FormLayout formLogin= new FormLayout(username,password,btnLogin);
        formLogin.setMargin(false);
        //
        Panel loginPanel= new Panel("Iniciar Sesion",formLogin);
        loginPanel.setStyleName("primary");
        loginPanel.setWidth("500");
        //
        //
        vrt.addComponent(formLogin);
        vrt.setComponentAlignment(formLogin,Alignment.MIDDLE_CENTER);

        //
        btnLogin.addClickListener(e-> login());
        //
        setResizable(false);
        setModal(true);
        center();
        setHeight("200");
        setWidth("400");
        setClosable(true);
        setDraggable(false);
        setContent(vrt);
    }

    /**
     * Metodo el cual verifica si los datos introducidos hayan sido correctos.
     * En el caso de que lo sean procede a realizar el inicio de sesion, en caso
     * contrario se mandara un error entorno al ingreso incorrecto de datos.
     */
    public void login()
    {
        Notification aux = null;
        account = new Account(username.getValue(),password.getValue().toString());
        if(accountController.existsById(account))
        {
            aux= new Notification("Success","Welcome!", Notification.TYPE_HUMANIZED_MESSAGE);
            aux.setPosition(Position.BOTTOM_CENTER);
            aux.show(Page.getCurrent());
            this.close();
        }
        else
        {
            aux= new Notification("Username or Password incorrect",Notification.TYPE_WARNING_MESSAGE);
            aux.setPosition(Position.BOTTOM_CENTER);
            aux.show(Page.getCurrent());
        }
    }
}
