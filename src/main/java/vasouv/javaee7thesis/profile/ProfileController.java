/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.profile;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import vasouv.javaee7thesis.courses.Course;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.login.AuthenticationEJB;

/**
 *
 * @author vasouv
 */
@Named(value = "profileController")
@RequestScoped
public class ProfileController {

    @EJB
    CourseFacade courseFacade;
    
    @EJB
    AuthenticationEJB auth;
    
    List<Course> userCourses;
    
    //View properties for rendering of the buttons
    boolean javaee, netbeans, webservices, java8;
    
    @PostConstruct
    public void init() {
        userCourses.addAll(courseFacade.findCourseNamesByUser(auth.getUser().getUsername()));
        checkCourses();
    }
    
    public ProfileController() {
        userCourses = new ArrayList();
        javaee = false;
        netbeans = false;
        webservices = false;
        java8 = false;
    }
    
    /**
     * Renders the Course buttons.
     * 
     * The userCourses List holds all the Courses for the User. It's streamed and checked whether
     * the Course titles they're enrolled in, match the general Course titles available.
     */
    private void checkCourses() {
        userCourses.stream().filter((c) -> (c.getTitle().equalsIgnoreCase("Learning Java EE 7"))).forEach((_item) -> {
            javaee = true;
        });
        userCourses.stream().filter((c) -> (c.getTitle().equalsIgnoreCase("Developing Applications with NetBeans 8"))).forEach((_item) -> {
            netbeans = true;
        });
        userCourses.stream().filter((c) -> (c.getTitle().equalsIgnoreCase("Web Services"))).forEach((_item) -> {
            webservices = true;
        });
        userCourses.stream().filter((c) -> (c.getTitle().equalsIgnoreCase("Java8"))).forEach((_item) -> {
            java8 = true;
        });
    }
    
    // ----- GETTERS & SETTERS -----

    public boolean isJavaee() {
        return javaee;
    }

    public void setJavaee(boolean javaee) {
        this.javaee = javaee;
    }

    public boolean isNetbeans() {
        return netbeans;
    }

    public void setNetbeans(boolean netbeans) {
        this.netbeans = netbeans;
    }

    public boolean isWebservices() {
        return webservices;
    }

    public void setWebservices(boolean webservices) {
        this.webservices = webservices;
    }

    public boolean isJava8() {
        return java8;
    }

    public void setJava8(boolean java8) {
        this.java8 = java8;
    }
    
    
}
