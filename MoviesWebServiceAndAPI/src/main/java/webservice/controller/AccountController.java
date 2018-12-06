package webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.Account;
import webservice.service.AccountService;

/**
 * Clase que tiene como funcion realizar la validacion y acciones entorno
 * a los usuarios.
 */
@Component
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    /**
    * Metodo cuya funcion es realizar la creacion de los usuarios
    * @param account The account to create.
    */
    public void createAccount(Account account) {
        accountService.createAccount(account);
    }
    
     /**
    * Metodo cuya funcion es actualizar la contraseña del usuario.
    * @param account La cuenta del usuario al cual se desea actualizar.
    */
    public Account updateAccountPassword(Account account)
    {
        return accountService.updateAccountPassword(account);
    }
    
     /**
    * Metodo de validacion el cual verifica la existencia de un usuario en especial.
    * @param account El usuario a verificar.
    */
    public boolean exists(Account account) {
        return accountService.exists(account);
    }
    
     /**
    * Metodo curya funcion es la verificacion de la existencia del username de un 
    * usuario
    * @param username el nombre del usuario a verificar.
    */
    public boolean existsById(String username) {
        return accountService.existsById(username);
    }
    
     /**
    * Metodo cuya funcion es la obtencion de un usuario apartir de su identificador
    * @param id El id del usuario.
    */
    public Account getAccount(String id)
    {
        return accountService.getAccount(id);
    }
    
     /**
    * Metodo cuya funcon es editar la descripcion del usuario.
    * @param account la cuenta a actualizar la informacion.
    */
    public Account editAccountDescription(Account account)
    {
        return accountService.editAccountDescription(account);
    }
    
     /**
    * Metodo cuya funcion es la eliminacion de un usuario.
    * @param id identificador del usuario.
    */
    public  void deleteAccount(String id){
        accountService.deleteAccount(id);
    }
}
