/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.login.AuthenticationEJB;
import vasouv.javaee7thesis.shoppingcart.ShoppingCart;

/**
 *
 * @author vasouv
 */
@Named("showCoursesJSFBean")
@SessionScoped
public class ShowCoursesJSFBean implements Serializable {

    @EJB
    private CourseFacade coursesFacade;
    
    @EJB
    private AuthenticationEJB auth;
    
    //Injecting the CDI bean so we can add courses at will
    @Inject
    private ShoppingCart shoppingCart;
    
    //This list holds the data retrieved from the DB
    private List<Course> courses;
    
    //Disables the Buy button if the user has already bought the course
    private boolean hasBoughtJavaEE, hasBoughtNetbeans, hasBoughtWebServices, hasBoughtJava8;
    
    @PostConstruct
    public void init() {
        //Calls the Courses EJB and adds the findAll List to the courses List
        courses.addAll(coursesFacade.findAll());
        handleBuyButton();
    }
    
    public ShowCoursesJSFBean() {
        this.courses = new ArrayList();
        hasBoughtJava8 = false;
        hasBoughtJavaEE = false;
        hasBoughtNetbeans = false;
        hasBoughtWebServices = false;
    }
    
    /**
     * TODO: Quick and dirty adding to cart.
     * 
     * Needs a much better implementation
     * 
     * @param c String entered to know which course to add
     */
    public void addToShoppingCart(String c) {
        switch (c) {
            case "0": shoppingCart.addCourse(courses.get(0)); break;
            case "1": shoppingCart.addCourse(courses.get(1)); break;
            case "2": shoppingCart.addCourse(courses.get(2)); break;
            case "3": shoppingCart.addCourse(courses.get(3)); break;
        }
    }
    
    /**
     * Handles the disabled attribute of the Buy button.
     * 
     * If a user has already bought some courses, the Buy button is disabled for those courses.
     */
    private void handleBuyButton() {
        List<Course> userHasBoughtCourses = coursesFacade.findCourseNamesByUser(auth.getUser().getUsername());
        
        for (Course c : userHasBoughtCourses) {
            if (c.getTitle().equalsIgnoreCase("Learning Java EE 7")) hasBoughtJavaEE = true;
            if (c.getTitle().equalsIgnoreCase("Developing Applications with NetBeans 8")) hasBoughtNetbeans = true;
            if (c.getTitle().equalsIgnoreCase("Web Services")) hasBoughtWebServices = true;
            if (c.getTitle().equalsIgnoreCase("Java8")) hasBoughtJava8 = true;
        }
    }
    
    // GETTERS & SETTERS

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> tempCourses) {
        this.courses = tempCourses;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean isHasBoughtJavaEE() {
        return hasBoughtJavaEE;
    }

    public void setHasBoughtJavaEE(boolean hasBoughtJavaEE) {
        this.hasBoughtJavaEE = hasBoughtJavaEE;
    }

    public boolean isHasBoughtNetbeans() {
        return hasBoughtNetbeans;
    }

    public void setHasBoughtNetbeans(boolean hasBoughtNetbeans) {
        this.hasBoughtNetbeans = hasBoughtNetbeans;
    }

    public boolean isHasBoughtWebServices() {
        return hasBoughtWebServices;
    }

    public void setHasBoughtWebServices(boolean hasBoughtWebServices) {
        this.hasBoughtWebServices = hasBoughtWebServices;
    }

    public boolean isHasBoughtJava8() {
        return hasBoughtJava8;
    }

    public void setHasBoughtJava8(boolean hasBoughtJava8) {
        this.hasBoughtJava8 = hasBoughtJava8;
    }

}
