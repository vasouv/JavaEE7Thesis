/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.register.sessionbeans;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Named
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;
    
    public void persistUser(User us) {
        em.persist(us);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
