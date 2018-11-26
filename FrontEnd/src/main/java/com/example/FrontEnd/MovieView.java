package com.example.FrontEnd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.Movie;
import com.vaadin.annotations.Title;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import controller.MovieController;

@Title("MovieService")
public class MovieView extends Composite implements View {
	
	TextField searchBar= new TextField();
	Button LogBT= new Button("LogIn");
    MenuBar header= new MenuBar();
    VerticalLayout layout= new VerticalLayout();
    Navigator nav;
    ArrayList<Image> listb= new ArrayList<>();
    @Autowired
    MovieController movieControl= new MovieController();
    ClickListener showPopup;
	public MovieView(Navigator nav) 
	{
		this.nav=nav;
		nav.addView("login", new LoginView(nav));
		InicializarMainView();
	    //---------------------------------------
	    //Se le agrega los contenidos al UI
	    //-------------Zona de eventos------------
	    for (Image bt: listb) {
	    	showPopup= e-> showSubWindow(bt);
	    	bt.addClickListener(showPopup);
	    	//bt.addClickListener(e -> showSubWindow(bt));
	    }
        
	    setCompositionRoot(layout);
	}
	public void InicializarMainView() {
	    	 
	        HorizontalLayout top= new HorizontalLayout();
	        LogBT.addListener(e-> nav.navigateTo("login"));
	        top.addComponents(searchBar,LogBT);
	        HorizontalLayout bottom= new HorizontalLayout();
	        //-----------------MenuBar----------------
	        //header.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
	        //top.setWidth("100%");
	        /*top.addComponents(searchBar,header);
	        MenuBar.MenuItem profile= header.addItem("Profile",null);
	        header.addListener(e-> nav.navigateTo("loginView"));
	        MenuBar.MenuItem Infor= profile.addItem("UserInfo",null);*/
	        //-----------------------------------------
	        layout.addComponents(top);
	        
	        //Zona de prueba para el mostrado de peliculas
	        /*List<Movie> movielist = movieControl.getAllMovies();
	        int count=0;
	        for(Movie mov: movielist) 
	        {
	        	HorizontalLayout hor= new HorizontalLayout();
		        hor.setWidth("100%");
		        Image movieI= new Image(mov.getTitle());
		        movieI.setSource(new ExternalResource(mov.getPoster_path()));
		        movieI.setHeight(250,Unit.PIXELS);
		        movieI.setWidth(250, Unit.PIXELS);
		        listb.add(movieI);
                hor.addComponent(movieI);
                hor.setExpandRatio(movieI, 100f);
                count++;
                if(count==4) {count=0;layout.addComponent(hor);}
	        }*/
		    for(int i=0; i<=4; i++)
		    {
		    	HorizontalLayout hor= new HorizontalLayout();
		        hor.setWidth("100%");
		        for (int c=0; c<=4; c++)
		        {
		                Image but= new Image("Poster"+i);
		                but.setSource(new ExternalResource("https://www.gstatic.com/webp/gallery/1.jpg"));
		                but.setHeight(250,Unit.PIXELS);
		                but.setWidth(250, Unit.PIXELS);
		                
		                listb.add(but);
		                hor.addComponent(but);
		                hor.setExpandRatio(but, 100f);
		        }
		            layout.addComponent(hor);
		    }
	    }
	    
	@SuppressWarnings("deprecation")
	public  void showSubWindow(Image clickedImage){
			Image posterIm= new Image();
			posterIm.setSource(clickedImage.getSource());
	        SubWindow mySub= new SubWindow(posterIm);
	        com.vaadin.ui.UI.getCurrent().addWindow(mySub);
	}

}
