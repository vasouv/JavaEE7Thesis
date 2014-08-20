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
     * @param u The User to be persisted to the database.
     */
    @Override
    public void create(User u) {
        
        PasswordHasher ph = new PasswordHasher();
        
        //The password from the backing bean is used as an argument for the
        //passwordhasher and its SHA-256 output is set to the actual pass property
        u.setPassword(ph.generateHash(u.getPassword()));
        
        em.persist(u);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
