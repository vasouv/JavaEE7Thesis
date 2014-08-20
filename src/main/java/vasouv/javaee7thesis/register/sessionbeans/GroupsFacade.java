/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.register.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.register.Groups;

/**
 *
 * @author vasouv
 */
@Stateless
public class GroupsFacade extends AbstractFacade<Groups> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;
    
    /**
     * Persists the Group to the database.
     * 
     * The Group argument is persisted to the database, after its id property
     * is set to the corresponding id to be inserted.
     * 
     * @param g The Group to be persisted to the database.
     */
    @Override
    public void create(Groups g) {
        em.persist(g);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupsFacade() {
        super(Groups.class);
    }
    
}
