/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.courses.Course;

/**
 *
 * @author vasouv
 */
@Stateless
public class CourseFacade extends AbstractFacade<Course> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseFacade() {
        super(Course.class);
    }
    
    /**
     * Selects all courses.
     * 
     * This method queries the DB and fetches a list of all available courses.
     * 
     * @return List containing all Course entities.
     */
    @Override
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c").getResultList();
    }
    
    public Integer findCourseMaxID() {
        return (Integer)em.createQuery("select max(c.idcourse) from Course c").getSingleResult();
    }
    
}
