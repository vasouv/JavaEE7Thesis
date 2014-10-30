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
import vasouv.javaee7thesis.courses.Lectures;

/**
 *
 * @author vasouv
 */
@Stateless
public class LecturesFacade extends AbstractFacade<Lectures> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LecturesFacade() {
        super(Lectures.class);
    }
    
    public List<String> findByCourseName(){
        return em.createNativeQuery("select l.title from COURSES c, LECTURES l, COURSELECTURES cl where c.id=cl.course_id and cl.lecture_id=l.id and c.title='Developing Applications with NetBeans 8'").getResultList();
    }
    
}
