package vasouv.javaee7thesis.register;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

    //Injects the EJBs that will persist the User's credentials upon registration
    @EJB
    UserFacade userEJB;
    @EJB
    GroupsFacade groupEJB;

    private User user;
    private Groups group;

    //These are the four properties that the user enters in the form
    private String username;
    private String password;
    private String name;
    private String email;

    public RegisterUserJSFBean() {
        this.user = new User();
        this.group = new Groups();
    }

    /**
     * Registers the new user and navigates to the registersuccess webpage.
     *
     * @return the webpage for successful registration
     */
    public String registerNewUser() {
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);
        group.setUsername(this.username);
        group.setGroupname("user");

        userEJB.create(user);
        groupEJB.create(group);

        return "/registersuccess.xhtml";
    }

    //GETTERS & SETTERS
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
