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
    
    public Courses findSingle() {
//        return em.createQuery("select u from User u where u.name like :search")
//                .setParameter("search", "%" + name + "%").getResultList();
        System.out.println("in the findSingle method");
        return (Courses) em.createNamedQuery("Courses.findByTitle").setParameter("title", "Learning Java EE 7").getSingleResult();
    }
    
}
