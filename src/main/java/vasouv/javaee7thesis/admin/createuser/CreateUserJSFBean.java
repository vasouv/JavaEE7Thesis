/*
 * This JSF Bean is similar to the RegisterNewUserJSFBean. Maybe I could have
 * just extended that bean instead of copying the code as is and changing some
 * lines here and there. For now, I'm keeping it like so simply because it's the
 * backing bean of this specific webpage.
 */

package vasouv.javaee7thesis.admin.createuser;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.register.Groups;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.GroupsFacade;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;

/**
 *
 * @author vasouv
 */
@RequestScoped
@Named("createUserJSFBean")
public class CreateUserJSFBean implements Serializable {

    //Injects the EJBs that will persist the User's credentials upon creation
    @EJB
    UserFacade userEJB;
    @EJB
    GroupsFacade groupEJB;

    private User user;
    private Groups group;

    //These are the five properties that the user enters in the form
    private String username;
    private String password;
    private String name;
    private String email;
    private String grp;
    
    public CreateUserJSFBean() {
        this.user = new User();
        this.group = new Groups();
    }
    
    public String createNewUser() {
        inputCredentials();
        
        userEJB.create(user);
        groupEJB.create(group);
        
        return "admin-createuser-complete";
    }

    private void inputCredentials() {
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        group.setGroupname(grp);
        group.setUsername(username);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(getName());
        sb.append("\nEmail: ");
        sb.append(getEmail());
        sb.append("\nUsername: ");
        sb.append(getUsername());
        sb.append("\nPassword: ");
        sb.append(getPassword());
        return sb.toString();
    }

    /*
    * GETTERS AND SETTERS
    */
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

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }
    
    
}
