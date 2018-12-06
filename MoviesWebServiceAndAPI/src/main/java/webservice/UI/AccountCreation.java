package webservice.UI;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.controller.AccountController;
import webservice.model.Account;

/**
 * Clase que extiende a Window el cual contiene los metodos y constructores para realizar
 * de forma concreta las acciones a realizar de la ventana Creacion de Cuenta.
 */
@Component
public class AccountCreation extends Window {

    @Autowired
    private AccountController accountController;

    private TextField accountField;
    private PasswordField passwordField;
    private Button accountButton;

    public AccountCreation() {
        super("Account creation");

        VerticalLayout main = new VerticalLayout();

        accountField = new TextField();
        passwordField = new PasswordField();
        accountButton = new Button("Create account");

        main.addComponents(accountField, passwordField, accountButton);

        accountField.setCaption("Enter new username");
        passwordField.setCaption("Enter new password");

        accountButton.addListener(e -> createAccount());
        accountButton.setStyleName("primary");

        setResizable(false);
        setModal(true);
        center();
        setHeight("260");
        setWidth("230");
        setClosable(true);
        setDraggable(false);
        setContent(main);
    }
    
    /**
    * Metodo el cual su funcion es realizar la accion de la creacion de una nueva cuenta de usuario.
    */
    private void createAccount() {
        accountController.createAccount(new Account(accountField.getValue(),
                passwordField.getValue()));
        Notification succesPrompt = new Notification("Account successfully created", Notification.
                TYPE_HUMANIZED_MESSAGE);
        succesPrompt.setPosition(Position.BOTTOM_CENTER);
        succesPrompt.show(Page.getCurrent());

        accountField.clear();
        passwordField.clear();
    }
}
