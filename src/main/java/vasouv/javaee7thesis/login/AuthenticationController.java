package vasouv.javaee7thesis.login;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vasouv.javaee7thesis.register.Groups;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.GroupsFacade;

/**
 * 
 * @author Josh Juneau - JavaEE 7 Recipes - Ch14.3 - Apress
 */
@Named(value = "authenticationJSFBean")
@SessionScoped
public class AuthenticationController implements Serializable {

    //The EJB that will try to authenticate with given credentials
    @EJB
    private AuthenticationEJB authenticationFacade;
    
    @EJB
    private GroupsFacade groupsFacade;
    
    //Given username
    private String username;
    
    //Will contain the user's username
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
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }
    
    /**
     * Logs in the user.
     * 
     * Sets the values of the form to the User. The User is passed to the EJB
     * in order to log in. The authResult holds the boolean whether the User
     * has authenticated or not. If they have, the User's credentials are re-set
     * to the JSF User object for the session and navigate to the users' main page.
     * If not, the User gets nulled and navigate to the loginerror page due to
     * invalid credentials.
     * 
     * @return Webpage for correct or false authentication
     */
    public String login() {
        
        String pageToNavigate;
        
        //Passes the JSF User to the EJB
        authenticationFacade.setUser(getUser());
        
        //Holds the boolean of whether has correctly authenticated or not
        boolean authResult = authenticationFacade.login();

        //Navigate to the main page or login error page
        if (authResult) {
            this.authenticated = true;

            setUser(authenticationFacade.getUser());
            
            //Group of the specified username
            Groups gr = groupsFacade.findGroupByUsername(authenticationFacade.getUser());
            
            //Changes the pageToNavigate according to the groupname
            if(gr.getGroupname().equalsIgnoreCase("user")) {
                pageToNavigate = "users/main.xhtml";
            }
            else {
                pageToNavigate = "admin/admin-main.xhtml";
            }

            return pageToNavigate;
        } else {
            this.authenticated = false;
            setUser(null);
            return "loginerror";
        }

    }

    /**
     * Logs out the user.
     * 
     * Nulls the JSF User object, gets the current session and invalidates it,
     * finally navigates to the logout webpage.
     * 
     * @return Logout webpage
     */
    public String logout() {
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "/logout.xhtml";
    }

    //GETTERS & SETTERS

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
