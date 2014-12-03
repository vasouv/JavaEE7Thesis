/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.checkout;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.login.AuthenticationEJB;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;
import vasouv.javaee7thesis.shoppingcart.ShoppingCart;

/**
 *
 * @author vasouv
 */
@Named("checkoutBean")
@RequestScoped
public class CheckoutBean implements Serializable {

    @Inject
    private ShoppingCart shoppingCart;
    
    @EJB
    private CourseFacade courseFacade;
    
    @EJB
    private UserFacade userFacade;
    
    //Retrieves the logged in User's username
    @EJB
    private AuthenticationEJB auth;
    
    //Shows the username in the View
    private String username;
    
    //Credit card number - simply a String
    private String creditCard;
    
    @PostConstruct
    public void init() {
        //Sets the username so it shows upon View time
        username = auth.getUser().getUsername();
    }
    
    public CheckoutBean() {
    }
    
    /**
     * Finalizes the purchase.
     * 
     * @return Navigates to the Checkout Complete page.
     */
    public String finalizePurchase(){
        persistUserCourses();
        clearShoppingCart();
        return "checkoutcomplete";
    }
    
    /**
     * Sets the User - Courses relationship to the DB.
     * 
     * A temp User is retrieved from the DB. For each of the Courses in the shopping
     * cart, the relationship is persisted to the DB.
     */
    private void persistUserCourses() {
        
        //Retrieves the User entity from the DB
        User use = userFacade.findByUsernameSingle(username);
        
        //For every course in the cart, calls the method to persist the users
        //Uses Java8 streams and Lambdas
        shoppingCart.getCoursesToBuy().stream().forEach((co1) -> {
            courseFacade.setCourseUser(use, co1.getIdcourse());
        });
        
    }
    
    /**
     * Clears the shopping cart.
     */
    private void clearShoppingCart() {
        shoppingCart.clearCart();
    }

    // --- GETTERS & SETTERS ---
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    
    
    
}
