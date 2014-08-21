/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.register.sessionbeans;

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
        
        em.persist(u);
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
