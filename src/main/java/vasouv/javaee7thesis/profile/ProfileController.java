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
    private CourseFacade courseFacade;
    
    @EJB
    private AuthenticationEJB auth;
    
    private List<Course> userCourses;
    
    //View properties for rendering of the buttons
    private boolean javaee, netbeans, webservices, java8;
    
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
        for (Course c : userCourses) {
            if (c.getTitle().equalsIgnoreCase("Learning Java EE 7")) javaee = true;
            if (c.getTitle().equalsIgnoreCase("Developing Applications with NetBeans 8")) netbeans = true;
            if (c.getTitle().equalsIgnoreCase("Web Services")) webservices = true;
            if (c.getTitle().equalsIgnoreCase("Java8")) java8 = true;
        }
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
