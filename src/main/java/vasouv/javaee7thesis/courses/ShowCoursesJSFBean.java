/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;

/**
 *
 * @author vasouv
 */
@Named("showCoursesJSFBean")
public class ShowCoursesJSFBean implements Serializable {

    @EJB
    CourseFacade coursesFacade;
    
    @EJB
    LectureFacade lecturesFacade;
    
    //This list holds the data retrieved from the DB, its contents
    //will be put into a HashMap
    List<Course> courses;
    
    @PostConstruct
    public void init() {
        courses.addAll(coursesFacade.findAll());
    }
    
    public ShowCoursesJSFBean() {
        this.courses = new ArrayList();
    }
    
    // GETTERS & SETTERS

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> tempCourses) {
        this.courses = tempCourses;
    }

}
