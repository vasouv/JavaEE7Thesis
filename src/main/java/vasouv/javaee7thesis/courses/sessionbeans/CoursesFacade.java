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
import vasouv.javaee7thesis.courses.Courses;
import vasouv.javaee7thesis.courses.Lectures;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Stateless
public class CoursesFacade extends AbstractFacade<Courses> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoursesFacade() {
        super(Courses.class);
    }
    
//    @Override
//    public List<Courses> findAll() {
//        return em.createQuery("select c from Courses c INNER JOIN c.lecturesList l where l.id=c.id").getResultList();
//    }
    
    public Courses findSingle() {
        System.out.println("in the findSingle method");
        return (Courses) em.createNamedQuery("Courses.findByTitle").setParameter("title", "Learning Java EE 7").getSingleResult();
    }
    
//    public List<Lectures> findByCourseName(){
//        return em.createQuery("select lec from Lectures lec where lec.course.title = :cname").setParameter("cname", "Learning Java EE 7").getResultList();
//    }
    
}
