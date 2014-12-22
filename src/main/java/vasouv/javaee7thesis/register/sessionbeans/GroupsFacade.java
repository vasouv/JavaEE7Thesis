/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.register.sessionbeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.register.Groups;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Stateless
public class GroupsFacade extends AbstractFacade<Groups> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;
    
    //This logger is used to console print when a group is created
    private static final Logger glog = Logger.getLogger("groupLogger");
    
    /**
     * Persists the Group to the database.
     * 
     * The Group argument is persisted to the database, after its id property
     * is set to the corresponding id to be inserted.
     * 
     * Logs to the console the username and group.
     * 
     * @param g The Group to be persisted to the database.
     */
    @Override
    public void create(Groups g) {
        
        //Persists to the DB
        em.persist(g);
        
        //Logs to the console the username and group that was persisted to the DB
        glog.log(Level.INFO,"Username {0}" + " " + "was added in group: {1}", 
                new Object[]{g.getUsername(), g.getGroupname()});
    }
    
    /**
     * Deletes Group by User's username.
     * 
     * @param us Group to be deleted
     */
    @RolesAllowed("admin")
    public void deleteUser(User us) {
        int delCount = em.createQuery("delete from Groups g where g.username = :del")
                .setParameter("del", us.getUsername()).executeUpdate();
    }
    
    public Groups findGroupByUsername(User us) {
        return (Groups)em.createQuery("select g from Groups g where g.username = :name").setParameter("name", us.getUsername()).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupsFacade() {
        super(Groups.class);
    }
    
}
