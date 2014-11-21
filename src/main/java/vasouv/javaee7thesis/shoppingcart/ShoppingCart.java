/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.shoppingcart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.Course;

/**
 *
 * @author vasouv
 */
@Named("shoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {

    List<Course> coursesToBuy;
    
    Course selectedToRemove;
    
    public ShoppingCart() {
        coursesToBuy = new ArrayList();
    }
    
    public void addCourse(Course c) {
        coursesToBuy.add(c);
    }
    
    public void removeCourse() {
        coursesToBuy.remove(selectedToRemove);
    }
    
    public int getTotalPrice() {
        int total = 0;
        total = coursesToBuy.stream().map((c) -> c.getPrice()).reduce(total, Integer::sum);
        return total;
    }
    
    public String buyCourses() {
        return "checkout";
    }
    
    public void clearCart() {
        coursesToBuy.clear();
    }
    
    // ----- GETTERS & SETTERS -----

    public List<Course> getCoursesToBuy() {
        return coursesToBuy;
    }

    public void setCoursesToBuy(List<Course> coursesToBuy) {
        this.coursesToBuy = coursesToBuy;
    }

    public Course getSelectedToRemove() {
        return selectedToRemove;
    }

    public void setSelectedToRemove(Course selectedToRemove) {
        this.selectedToRemove = selectedToRemove;
    }
    
}
