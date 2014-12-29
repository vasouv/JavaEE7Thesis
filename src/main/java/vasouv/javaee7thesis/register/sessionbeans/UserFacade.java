/*
* Even though this Entity class was primarily created for registering new users
* and persisting their data to the DB, it can be used for other operations as
* well; after all it's not normal to create another same Entity class and just
* change the functionality. I'll just be calling which methods I want from
* the appropriate place.
*/

package vasouv.javaee7thesis.register.sessionbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vasouv.javaee7thesis.passwordhashing.PasswordHasher;
import vasouv.javaee7thesis.register.User;

/**
 *
 * @author vasouv
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "vasouvPU")
    private EntityManager em;
    
    //The logger is used to log to the console when a user registers a new account
    private static final Logger log = Logger.getLogger("registerLogger");
    
    /**
     * Persists the User to the database.
     * 
     * The method overrides the one from AbstractFacade and persists the User
     * to the database, after changing the password property into its SHA-256
     * value. The User that is passed as an argument, has already the String
     * password that was entered in the form. Prior to persisting the User,
     * the password must be converted to its SHA-256 hash and this is done with
     * the help of the PasswordHasher.
     * 
     * Also, the ID of the User is set by retrieving the currently max(id) and
     * adding 1 so it becomes one bigger. Could have done this by having the DB
     * auto-generating the IDs but forgot :P
     * 
     * Logs to the console the User's name that created a new account.
     * 
     * @param u The User to be persisted to the database.
     */
    @Override
    public void create(User u) {
        
        PasswordHasher ph = new PasswordHasher();
        
        //The password from the backing bean is used as an argument for the
        //passwordhasher and its SHA-256 output is set to the actual pass property
        u.setPassword(ph.generateHash(u.getPassword()));
        
        //It calls the method to find the max(id) and adds 1
        u.setId(findMaxID() + 1);
        
        //Persists the User to the DB
        em.persist(u);
        
        //Logs to the console the name of the user after persisting it to the DB
        log.log(Level.INFO,"User {0}" + " " + "has just created an account!", u.getName());
    }
    
    /**
     * Finds the max(id) of the Users.
     * 
     * It queries the DB and retrieves the max(id) of all the Users. It's usage
     * is for the assignment of the id of the User to be registered. 
     * See method create(User u) for usage.
     * 
     * @return Integer the max value of the IDs. Single result.
     */
    private Integer findMaxID() {
        return (Integer)em.createQuery("select max(u.id) from User u").getSingleResult();
    }
    
    /**
     * Shows all Users.
     * 
     * The method queries the DB and retrieves all User entities. Basically it's
     * just a "select * from users". Used in the admin page that displays all users.
     * 
     * @return List(User) retrieves all Users
     */
    @RolesAllowed("admin")
    public List<User> findAllUsers() {
        return em.createQuery("select u from User u").getResultList();
    }
    
    /**
     * Finds users by username - Java8 streams.
     * 
     * This method uses the Java 8 Lambda expressions and Collection improvements
     * to act as an SQL query.
     * 
     * First, the List allUsers is created and populated with all of the database
     * records. Then, a Collection stream is applied to the list and filters the
     * elements if the username property contains the username argument. Lastly,
     * it's returned as a List and added to the usernameUsers one.
     * 
     * @param username
     * @return List(User) retrieves the matched Users with username like param
     */
    @RolesAllowed("admin")
    public List<User> findByUsername(String username) {
        
        List<User> allUsers = findAllUsers();
        
        List<User> usernameUsers = allUsers.stream()
                .filter((u) -> u.getUsername().contains(username))
                .collect(Collectors.toList());
        
        return usernameUsers;
    }
    
    /**
     * Finds users by name.
     * 
     * @param name
     * @return List(User) retrieves the matched Users with name like param
     */
    @RolesAllowed("admin")
    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name like :search")
                .setParameter("search", "%" + name + "%").getResultList();
    }
    
    /**
     * Finds users by email.
     * 
     * @param email
     * @return List(User) retrieves the matched Users with email like param
     */
    @RolesAllowed("admin")
    public List<User> findByEmail(String email) {
        return em.createQuery("select u from User u where u.email like :search")
                .setParameter("search", "%" + email + "%").getResultList();
    }
    
    /**
     * Deletes User by id.
     * 
     * @param us User to be deleted
     */
    @RolesAllowed("admin")
    public void deleteUser(User us) {
        int delCount = em.createQuery("delete from User u where u.id = :del")
                .setParameter("del", us.getId()).executeUpdate();
        
        log.log(Level.INFO,"User {0}" + " " + "was successfully removed!", us.getName());
    }
    
    public User findByUsernameSingle(String user) {
        return (User)em.createQuery("select u from User u where u.username = :us").setParameter("us", user).getSingleResult();
    }
    
    /**
     * Checks if given username exists in the DB.
     * 
     * @param usern username to be queried.
     * @return true if exist==1, meaning there's a record in the DB.
     */
    public boolean usernameExists(String usern) {
        String query = "select count(*) from Users where username='" + usern + "'";
        int exist = (int)em.createNativeQuery(query).getSingleResult();
        
        return exist != 0;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
