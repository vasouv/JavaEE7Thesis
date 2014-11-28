/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.singlecourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.Lecture;
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;
import vasouv.javaee7thesis.login.AuthenticationEJB;

/**
 * Abstract class to hold same functionality among all Course Beans.
 *
 * @author vasouv
 */
@Named
@RequestScoped
public class AbstractCourse implements Serializable {
    
    //Username of the user
    String username;
    
    //Will search for this courseName in the DB
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
    
    // ----- GETTERS & SETTERS -----

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
