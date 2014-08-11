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
    }
    
}
