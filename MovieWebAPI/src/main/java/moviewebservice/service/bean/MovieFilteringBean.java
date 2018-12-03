/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviewebservice.service.bean;

import javax.ws.rs.QueryParam;
/**
 * <h>Clase unica para el filtrado de los parametros que pueden tener al obtener
 * todas las peliculas</h>
 * @author diego
 */
public class MovieFilteringBean {
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    
}
