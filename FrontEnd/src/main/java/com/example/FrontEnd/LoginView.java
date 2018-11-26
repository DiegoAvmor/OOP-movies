package com.example.FrontEnd;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import com.example.model.Profile;
import  com.example.service.ProfileService;

public class LoginView extends Composite implements View {
	private ProfileService service= new ProfileService();
	private Profile profile;
	private TextField username;
	private PasswordField password;
	private Button btnLogin;
	public boolean success=false;
	Navigator nav;
	
	public LoginView(Navigator nav) 
	{
		this.nav=nav;
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
		//vrt.setComponentAlignment(formLogin,Alignment.MIDDLE_CENTER);
		
		//
		btnLogin.addClickListener(e-> login());
		//
		setCompositionRoot(vrt);
	}
	
	public void login() 
	{
		Notification aux = null;
		profile= new Profile(username.getValue(),password.getValue().toString());
		if(service.checkIfIsCorrect(profile)) 
		{
			aux= new Notification("Success","Welcome!", Notification.TYPE_HUMANIZED_MESSAGE);
			aux.setPosition(Position.BOTTOM_CENTER);
			aux.show(Page.getCurrent());
			success=true;
		}
		else 
		{
			aux= new Notification("Username or Password incorrect",Notification.TYPE_WARNING_MESSAGE);
			aux.setPosition(Position.BOTTOM_CENTER);
			aux.show(Page.getCurrent());
			success=false;
			
		}
		this.checkSuccess(success);
		//Verifico que no exista un perfil con el mismo username
		//
	}
	public void checkSuccess(boolean flag) 
    {
    	if(flag) 
    	{
    		nav.addView("movie", new MovieView(nav));
    		nav.navigateTo("movie");
    		System.out.println("YEAH");
    	}
    }
}
