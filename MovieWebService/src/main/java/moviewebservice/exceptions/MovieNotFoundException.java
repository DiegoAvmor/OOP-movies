/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviewebservice.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message) 
    {
        super(message);
    }
}
