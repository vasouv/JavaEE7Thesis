package vasouv.javaee7thesis.passwordhashing;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * This class uses the Google Guava library, in order to create a SHA-256 hash
 * for the user's password. The password is stored hashed in the database for
 * security reasons. This class will be used both for registering a new user
 * and for checking credentials upon logging in.
 * 
 * Original code from jlombardo (github)
 * 
 * @author vasouv
 */
public class PasswordHasher {
    
    public String generateHash(String password) {
        return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
    }
    
}
