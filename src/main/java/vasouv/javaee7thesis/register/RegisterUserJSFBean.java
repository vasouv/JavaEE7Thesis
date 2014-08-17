package vasouv.javaee7thesis.register;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.register.sessionbeans.GroupsFacade;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;

/**
 *
 * @author vasouv
 */
@RequestScoped
@Named("registerNewUser")
public class RegisterUserJSFBean implements Serializable {
    
    //here will be the EJB
    @EJB
    UserFacade userEJB;
    @EJB
    GroupsFacade groupEJB;
    
    private User user = new User();
    private Groups group = new Groups();
    
    //These are the four properties that the user enters in the form
    private String username;
    private String password;
    private String name;
    private String email;

    /**
     * Creates a new instance of RegisterUserManagedBean
     */
    public RegisterUserJSFBean() {

    }
    
    /**
     * Registers the new user and navigates to the registersuccess.xhtml
     * @return 
     */
    public String registerNewUser() {
        user.setId(22);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);
        group.setUsername(this.username);
        group.setGroupname("users");
        
        userEJB.persistUser(user);
        
        return "/registersuccess.xhtml";
    }
    
    
    /**
     * GETTERS AND SETTERS
     */

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
