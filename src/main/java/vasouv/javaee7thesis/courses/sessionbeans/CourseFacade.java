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
     * Sets the relationship between Courses and Users.
     * 
     * A course is retrieved from the DB with the specified courseID. The user param gets added
     * to the Course and the EntityManager merges the entities. The actual result is seen in the
     * USERCOURSES table.
     * 
     * @param u User to be set to the Courses
     * @param courseID Integer to find the Course by ID
     */
    public void setCourseUser(User u, int courseID) {
        Course c = (Course)em.createNamedQuery("Courses.findByIdcourse").setParameter("idcourse", courseID).getSingleResult();
        c.getUsers().add(u);
        em.merge(c);
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
