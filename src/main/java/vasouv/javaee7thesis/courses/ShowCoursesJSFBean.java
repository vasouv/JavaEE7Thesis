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
    CourseFacade coursesFacade;
    
    @EJB
    LectureFacade lecturesFacade;
    
    @Inject
    ShoppingCart shoppingCart;
    
    //This list holds the data retrieved from the DB
    List<Course> courses;
    
    @PostConstruct
    public void init() {
        //Calls the Courses EJB and adds the findAll List to the courses List
        courses.addAll(coursesFacade.findAll());
    }
    
    public ShowCoursesJSFBean() {
        this.courses = new ArrayList();
    }
    
    public void setTestCourse() {
        shoppingCart.addCourse(courses.get(0));
        shoppingCart.addCourse(courses.get(1));
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
