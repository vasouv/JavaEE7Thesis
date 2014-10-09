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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    
    //Queries the DB for the number of users - count()
    int numberOfUsers;
    
    /**
     * Sets data after the bean is created.
     * 
     * Since we can't use EJBs in a constructor, all data initialization needs
     * to happen in the @PostConstruct init() method. That's why the DataTable
     * and numberOfUsers indicator aren't populated in the constructor.
     */
    @PostConstruct
    public void init() {
        setUserList(userEJB.findAllUsers());
        setNumberOfUsers(userEJB.count());
    }
    
    public ShowAllUsersJSFBean() {
        this.userList = new ArrayList<>();
        this.searchBy = "username";
        this.searchTerm = "";
        this.numberOfUsers = 0;
    }
    
    /**
     * Searches the DB by sth.
     * 
     * Calls the specific method when we want to search by username, name or email.
     * If there is no search term, it retrieves all of the DB records.
     */
    public void searchByField(){
        if (searchTerm != null) {
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
        } else {
            searchAll();
        }
    }
    
    /**
     * Deletes the selected user.
     * 
     * When the DELETE button is pressed and a record is selected, first it's
     * removed from the Users table and then from the Groups. The Datatable is
     * repopulated with all the users from the DB, updates the numberOfUsers
     * indicator and growls the user's info.
     */
    public void deleteSelectedUser() {
        userEJB.deleteUser(selectedToDelete);
        groupEJB.deleteUser(selectedToDelete);
        setUserList(userEJB.findAllUsers());
        setNumberOfUsers(userEJB.count());
        showDeletedUser();
    }
    
    /**
     * Adds a FacesMessage for the Deleted User Growl.
     * 
     * It retrieves the FacesContext the action happened and adds a message
     * to the Growl element.
     */
    private void showDeletedUser() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User Deleted", selectedToDelete.getUsername()));
    }
    
    /**
     * Fetches all of the Users.
     * 
     * It uses the EJB to fetch all records from the Users table. It
     * then sets the List of users to the userList list.
     */
    private void searchAll() {
        setUserList(userEJB.findAllUsers());
        System.out.println("Search all was called");
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

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
    
}
