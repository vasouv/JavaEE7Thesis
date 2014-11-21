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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import vasouv.javaee7thesis.login.AuthenticationEJB;
import vasouv.javaee7thesis.shoppingcart.ShoppingCart;

/**
 *
 * @author vasouv
 */
@Named("checkoutBean")
@RequestScoped
public class CheckoutBean implements Serializable {

    @Inject
    ShoppingCart shoppingCart;
    
    @EJB
    AuthenticationEJB auth;
    
    String username;
    String creditCard;
    
    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
    }
    
    public CheckoutBean() {
    }
    
    public String finalizePurchase(){
        persistUserCourses();
        clearShoppingCart();
        return "checkoutcomplete";
    }
    
    private void persistUserCourses() {
        
    }
    
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
