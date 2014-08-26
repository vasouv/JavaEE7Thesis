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
    
    List<User> userList;
    
    public ShowAllUsersJSFBean() {
//        this.userList = new ArrayList<>();
//        this.userList = new ArrayList<>(userEJB.findAllUsers());
        this.userList = new ArrayList<>();
//        userList.add(new User(66,"t1","t1","t1","t1"));
//        userList.add(new User(67,"t2","t2","t2","t2"));
        System.out.println(userList.size());
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    
    
}
