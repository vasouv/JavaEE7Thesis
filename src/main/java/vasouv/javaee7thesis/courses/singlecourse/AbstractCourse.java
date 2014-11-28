/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.singlecourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.Lecture;
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;
import vasouv.javaee7thesis.login.AuthenticationEJB;

/**
 *
 * @author vasouv
 */
@Named
@RequestScoped
public class AbstractCourse implements Serializable {
    
    String username;
    String courseName;
    
    @EJB
    AuthenticationEJB auth;
    
    @EJB
    LectureFacade lectureFacade;
    
    List<Lecture> courseLectures;
    
    public AbstractCourse() {
        courseName = "";
        courseLectures = new ArrayList();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Lecture> getCourseLectures() {
        return courseLectures;
    }

    public void setCourseLectures(List<Lecture> courseLectures) {
        this.courseLectures = courseLectures;
    }
    
    
    
}
