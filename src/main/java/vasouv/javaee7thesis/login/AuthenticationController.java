/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.login;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Named(value = "authenticationJSFBean")
@SessionScoped
public class AuthenticationController implements Serializable {

    //The EJB that will try to authenticate with given credentials
    @EJB
    private AuthenticationEJB authenticationFacade;
    
    //Given username
    private String username;
    
    //Will be passed to the auth EJB so that the password isn't saved to the session
    private User user;
    
    //True if user is authenticated, false if not
    private boolean authenticated = false;
    
    //Initializes the HTTP session
    private HttpSession session = null;

    public AuthenticationController() {
    }

    /**
     * Retrieves the HTTP Session.
     * 
     * This is used to get the HTTP Session that started the request and identify
     * the user. It allows to store information for the user.
     * 
     * @return session
     */
    public HttpSession getSession() {
        // if(session == null){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }
    
    public String login() {
        authenticationFacade.setUser(getUser());
        boolean authResult = authenticationFacade.login();

        if (authResult) {
            this.authenticated = true;

            setUser(authenticationFacade.getUser());

            return "users/main.xhtml";
        } else {
            this.authenticated = false;
            setUser(null);
            return "loginerror";
        }

    }

    public String logout() {
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "logout";
    }

    /**
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        try {
            boolean auth = (Boolean) getSession().getAttribute("authenticated");
            if (auth) {
                this.authenticated = true;

            } else {
                authenticated = false;
            }
        } catch (Exception e) {
            this.authenticated = false;
        }

        return authenticated;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        this.username = getUser().getUsername();
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
        getUser().setUsername(username);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return authenticationFacade.getPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        authenticationFacade.setPassword(password);
    }

    public User getUser() {
        if (this.user == null) {
            user = new User();
            setUser(authenticationFacade.getUser());
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

}
