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
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;
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
    private LectureFacade lecturesFacade;
    
    //Injecting the CDI bean so we can add courses at will
    @Inject
    private ShoppingCart shoppingCart;
    
    //This list holds the data retrieved from the DB
    private List<Course> courses;
    
    @PostConstruct
    public void init() {
        //Calls the Courses EJB and adds the findAll List to the courses List
        courses.addAll(coursesFacade.findAll());
    }
    
    public ShowCoursesJSFBean() {
        this.courses = new ArrayList();
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

}
