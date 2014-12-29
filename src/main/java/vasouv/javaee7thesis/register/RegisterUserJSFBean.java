package vasouv.javaee7thesis.register;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
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
    private UserFacade userEJB;
    @EJB
    private GroupsFacade groupEJB;

    private User user;
    private Groups group;

    //These are the four properties that the user enters in the form
    private String username;
    private String password;
    private String name;

    //Validation pattern taken from Josh Juneau's JavaEE 7 Recipes, ch. 5-4, Solution 2
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid. Must be sample@mail.com")
    private String email;

    public RegisterUserJSFBean() {
        this.user = new User();
        this.group = new Groups();
    }

    /**
     * Registers the new user and navigates to the registersuccess webpage.
     * 
     * The username field checks the DB for the given value. If the username exists,
     * it throws a message. Otherwise, the registration completes.
     *
     * @return the webpage for successful registration
     */
    public String registerNewUser() {
        String webpage;
        if (checkIfUsernamePasswordExist()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exists", ""));
            webpage = "/register.xhtml";
        } else {
            user.setName(this.name);
            user.setEmail(this.email);
            user.setUsername(this.username);
            user.setPassword(this.password);
            group.setUsername(this.username);
            group.setGroupname("user");

            userEJB.create(user);
            groupEJB.create(group);
            
            webpage = "/registersuccess.xhtml";
        }

        return webpage;
    }

    /**
     * Checks if username exists.
     * 
     * Helper method that checks if the username already exists in the DB.
     * 
     * @return true or false, if the username exists.
     */
    private boolean checkIfUsernamePasswordExist() {
        return userEJB.usernameExists(username);
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
