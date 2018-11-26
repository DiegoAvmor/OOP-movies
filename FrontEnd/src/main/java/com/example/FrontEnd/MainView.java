package com.example.FrontEnd;


import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@EnableJpaRepositories
@Theme("valo")
@Title("WebService")
public class MainView extends UI {
	static Navigator nvi;
	//Objeto navegador, nos permitira movernos entre UIs dependiendo de los casos,
	//Por default siempre manda a la viste principal.
	protected static final String MOVIEVIEW= "movieView";
	protected static final String LOGINVIEW= "loginView";
	
    
    ArrayList<Button> listb= new ArrayList<>();//Este es la lista de objetos que nos servira para mostrar las peliculas
    
    final VerticalLayout layout= new VerticalLayout();

    @Override
    protected void init(VaadinRequest request) {
    	nvi= new Navigator(this,this);
    	
    	nvi.addView(MOVIEVIEW, new MovieView(this.nvi));
    	//nvi.addView(LOGINVIEW, lvi);
    	nvi.navigateTo(MOVIEVIEW);

    }
    
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
