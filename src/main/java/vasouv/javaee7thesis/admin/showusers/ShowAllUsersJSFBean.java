/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vasouv.javaee7thesis.admin.showusers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;

/**
 *
 * @author vasouv
 */
@ViewScoped
@Named("showAllUsersJSFBean")
public class ShowAllUsersJSFBean implements Serializable {

    @EJB
    UserFacade userEJB;
    
    //This List will hold the User elements from the DB, to be shown in the page
    List<User> userList;
    
    //Search variable for username, name and email
    String searchBy;
    
    //Search term given by the user
    String searchTerm;
    
    //When the bean is loaded, it sets all the users from the DB
    @PostConstruct
    public void init() {
        setUserList(userEJB.findAllUsers());
    }
    
    public ShowAllUsersJSFBean() {
        this.userList = new ArrayList<>();
        this.searchBy = "";
        this.searchTerm = "";
    }
    
    /**
     * Searches the DB by username.
     * 
     * It uses the EJB to search the DB for the specific users by username. It
     * then sets the List of users to the userList list.
     */
    public void searchByUsername() {
        setUserList(userEJB.findByUsername(searchTerm));
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
}
