/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.register.sessionbeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.passwordhashing.PasswordHasher;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;
    
    //The logger is used to log to the console when a user registers a new account
    private static final Logger log = Logger.getLogger("registerLogger");
    
    /**
     * Persists the User to the database.
     * 
     * The method overrides the one from AbstractFacade and persists the User
     * to the database, after changing the password property into its SHA-256
     * value. The User that is passed as an argument, has already the String
     * password that was entered in the form. Prior to persisting the User,
     * the password must be converted to its SHA-256 hash and this is done with
     * the help of the PasswordHasher.
     * 
     * Also, the ID of the User is set by retrieving the currently max(id) and
     * adding 1 so it becomes one bigger. Could have done this by having the DB
     * auto-generating the IDs but forgot :P
     * 
     * Logs to the console the User's name that created a new account.
     * 
     * @param u The User to be persisted to the database.
     */
    @Override
    public void create(User u) {
        
        PasswordHasher ph = new PasswordHasher();
        
        //The password from the backing bean is used as an argument for the
        //passwordhasher and its SHA-256 output is set to the actual pass property
        u.setPassword(ph.generateHash(u.getPassword()));
        
        //It calls the method to find the max(id) and adds 1
        u.setId(findMaxID() + 1);
        
        //Persists the User to the DB
        em.persist(u);
        
        //Logs to the console the name of the user after persisting it to the DB
        log.log(Level.INFO,"User {0}" + " " + "has just created an account!", u.getName());
    }
    
    /**
     * Finds the max(id) of the Users.
     * 
     * It queries the DB and retrieves the max(id) of all the Users. It's usage
     * is for the assignment of the id of the User to be registered. 
     * See method create(User u) for usage.
     * 
     * @return Integer the max value of the IDs. Single result.
     */
    private Integer findMaxID() {
        return (Integer)em.createQuery("select max(u.id) from User u").getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
