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
import vasouv.javaee7thesis.register.sessionbeans.GroupsFacade;
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
    
    @EJB
    GroupsFacade groupEJB;
    
    //This List will hold the User elements from the DB, to be shown in the page
    List<User> userList;
    
    //Search variable for username, name and email
    String searchBy;
    
    //Search term given by the user
    String searchTerm;
    
    //This User's account will be deleted
    private User selectedToDelete;
    
    //When the bean is loaded, it sets all the users from the DB
    @PostConstruct
    public void init() {
        setUserList(userEJB.findAllUsers());
    }
    
    public ShowAllUsersJSFBean() {
        this.userList = new ArrayList<>();
        this.searchBy = "username";
        this.searchTerm = "";
    }
    
    /**
     * Searches the DB by sth.
     * 
     * Calls the specific method when we want to search by username, name or email.
     * If the admin doesn't check a radio button, it searches by username by default.
     */
    public void searchByField(){
        switch (searchBy) {
            case "username":
                searchByUsername();
                break;
            case "name":
                searchByName();
                break;
            case "email":
                searchByEmail();
                break;
        }
    }
    
    public void deleteSelectedUser() {
        userEJB.deleteUser(selectedToDelete);
        groupEJB.deleteUser(selectedToDelete);
        setUserList(userEJB.findAllUsers());
    }
    
    /**
     * Searches the DB by username.
     * 
     * It uses the EJB to search the DB for the specific users by username. It
     * then sets the List of users to the userList list.
     */
    private void searchByUsername() {
        setUserList(userEJB.findByUsername(searchTerm));
    }
    
    /**
     * Searches the DB by name.
     * 
     * It uses the EJB to search the DB for the specific users by name. It
     * then sets the List of users to the userList list.
     */
    private void searchByName() {
        setUserList(userEJB.findByName(searchTerm));
    }
    
    /**
     * Searches the DB by email.
     * 
     * It uses the EJB to search the DB for the specific users by email. It
     * then sets the List of users to the userList list.
     */
    private void searchByEmail() {
        setUserList(userEJB.findByEmail(searchTerm));
    }

    /*
     * GETTERS AND SETTERS
    */
    
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

    public User getSelectedToDelete() {
        return selectedToDelete;
    }

    public void setSelectedToDelete(User selectedToDelete) {
        this.selectedToDelete = selectedToDelete;
    }
    
}
