package vasouv.javaee7thesis.register;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vasouv.javaee7thesis.courses.Course;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-04-24T19:30:46")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Course> courses;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}