package vasouv.javaee7thesis.courses.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.courses.Course;
import vasouv.javaee7thesis.register.User;

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
    
    /**
     * Finds the Course MAX ID.
     * 
     * @return Integer max id
     */
    public Integer findCourseMaxID() {
        return (Integer)em.createQuery("select max(c.idcourse) from Course c").getSingleResult();
    }
    
    /**
     * Finds the Courses for a specific User.
     * 
     * @param usro The User's username
     * @return List of Courses
     */
    public List<Course> findCourseNamesByUser(String usro) {
        return em.createQuery("select c from Course c inner join c.users us where us.username=:usr").setParameter("usr", usro).getResultList();
    }
    
}
