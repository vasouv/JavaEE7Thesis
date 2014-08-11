package vasouv.javaee7thesis.register;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author vasouv
 */
@RequestScoped
@Named("registerNewUser")
public class RegisterUserJSFBean implements Serializable {
    
    //here will be the EJB
    private User user;
    private Groups group;
    
    //These are the four properties that the user enters in the form
    private String username;
    private String password;
    private String name;
    private String email;

    /**
     * Creates a new instance of RegisterUserManagedBean
     */
    public RegisterUserJSFBean() {
        user = new User();
        group = new Groups();
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
