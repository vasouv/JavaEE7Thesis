/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.admin.showusers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;

/**
 *
 * @author vasouv
 */
@RequestScoped
@Named("showAllUsersJSFBean")
public class ShowAllUsersJSFBean implements Serializable {

    @EJB
    UserFacade userEJB;
    
    //This List will hold the User elements from the DB, to be shown in the page
    List<User> userList;
    
    public ShowAllUsersJSFBean() {
        this.userList = new ArrayList<>();
    }

    /**
     * Retrieves the list of Users for the admin view page.
     * 
     * It's a simple getter method for the userList variable. Before returning
     * the list of Users though, it uses the EJB to find all Users from the DB
     * and sets them to the userList variable.
     * 
     * @return List(User) all the DB User elements, basically (select * from User)
     */
    public List<User> getUserList() {
        setUserList(userEJB.findAllUsers());
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
}
