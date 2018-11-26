package com.example.FrontEnd;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SubWindow extends Window {
    public  SubWindow(Image sub)
    {
        super("SubVentana",sub);
        //------------------Establecimiento de los atributos-----------------------
    	VerticalLayout infoLayout= new VerticalLayout();
        HorizontalLayout sublayout= new HorizontalLayout();
    	Label description= new Label();
    	Panel moviePanel= new Panel();
    	Panel ratingPanel= new Panel();
    	Label ratingdes= new Label();
    	//---------------Asignacion de aspectos--------------------------
    	
    	ratingdes.setValue("IMD 10/10"+ "Metacritic 8/10");
    	ratingdes.setWidth("200px");
    	ratingPanel.setWidth("200px");
    	ratingPanel.setHeight("150px");
    	ratingPanel.setContent(ratingdes);
    	ratingPanel.setScrollLeft(0);
    	
    	description.setWidth("450px");
    	description.setValue("Aqui es donde va la descripcion de la pelicula, como pueden ver no hay problema con el tamaño del texto este se reajusta muy facilmente");
    	moviePanel.setWidth("450px");
    	moviePanel.setHeight("300px");
    	moviePanel.setCaption("Aqui va el titutlo de la pelicula");
    	
        Image subImage= sub;
        subImage.setHeight(250,Unit.PIXELS);
        subImage.setWidth(250, Unit.PIXELS);
        sublayout.addComponents(subImage,ratingPanel/*se le agregara un segundo componente que seran los ratings*/);
        //--------------------------------------------
        ratingPanel.setContent(ratingdes);
        moviePanel.setContent(description);
        infoLayout.addComponents(sublayout,moviePanel);
        setModal(true);
        center();
        setHeight("600");
        setWidth("500");
        setClosable(true);
        setDraggable(false);
        setContent(infoLayout);
    }
}
