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

    //Courses added to the shopping cart
    private List<Course> coursesToBuy;
    
    //Course being removed from the cart
    private Course selectedToRemove;
    
    public ShoppingCart() {
        coursesToBuy = new ArrayList();
    }
    
    /**
     * Adding a course to the cart.
     * 
     * @param c Course being added to the cart.
     */
    public void addCourse(Course c) {
        coursesToBuy.add(c);
    }
    
    /**
     * Removing a course from the cart.
     */
    public void removeCourse() {
        coursesToBuy.remove(selectedToRemove);
    }
    
    /**
     * Total price of the courses.
     * 
     * Total price of the cart courses, displayed in the courseshowcart.xhtml. Uses
     * Java8 streams to calculate the sum.
     * 
     * @return sum price
     */
    public int getTotalPrice() {
        int total = 0;
        total = coursesToBuy.stream().map((c) -> c.getPrice()).reduce(total, Integer::sum);
        return total;
    }
    
    /**
     * Navigates to the Checkout page.
     * 
     * @return checkout web page
     */
    public String buyCourses() {
        return "checkout";
    }
    
    /**
     * Clears the cart.
     */
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
