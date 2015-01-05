package vasouv.javaee7thesis.admin.createcourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.Course;
import vasouv.javaee7thesis.courses.Lecture;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;

/**
 *
 * @author vasouv
 */
@ViewScoped
@Named("createCourseJSFBean")
public class CreateCourseJSFBean implements Serializable {
    
    @EJB
    private CourseFacade courseFacade;
    
    @EJB
    private LectureFacade lectureFacade;
    
    //Objects to hold values from the View
    private List<Lecture> lectures;
    private Course course;
    
    //Active lecture ID for persistence
    private int lectureID;
    
    @PostConstruct
    public void init() {
        
        //Retrieves the max Lecture ID from the DB
        lectureID = lectureFacade.findLectureMaxID();
        
        //Populates the Lectures list with Lecture objects
        populateLectures();
    }
    
    public CreateCourseJSFBean() {
        this.lectures = new ArrayList();
        course = new Course();
    }
    
    /**
     * Creates a new Course.
     * 
     * When the admin presses the Create Course button,
     * the IDs for the Course and Lectures are set,
     * the Course connects to its Lectures and the
     * Course is saved to the DB.
     */
    public void createCourse() {
        setCourseLecturesIDs();
        addLecturesToTheCourse();
        persistCourse();
    }
    
    /**
     * Adds Lectures objects in the List.
     * 
     * Otherwise, throws NullPointerException. Lol, noob mistake!
     */
    private void populateLectures(){
        lectures.add(new Lecture());
        lectures.add(new Lecture());
        lectures.add(new Lecture());
    }
    
    /**
     * Sets the IDs for the Course and Lectures.
     * 
     * The Course ID is set by finding the max ID from the DB and incrementing by 1.
     * For each of the Lectures in the list, the lecturesID is first incremented by 1
     * and the courseID is set accordingly.
     */
    private void setCourseLecturesIDs() {
        course.setIdcourse(courseFacade.findCourseMaxID() + 1);
        for(Lecture currentLec : lectures) {
            lectureID++;
            currentLec.setCourseid(course);
            currentLec.setIdlecture(lectureID);
        }
    }
    
    /**
     * Adds Lectures to the Course.
     * 
     * The Lectures list is set to the Course object 
     * in order to satisfy the DB relationship.
     */
    private void addLecturesToTheCourse(){
        course.setLecturesList(lectures);
    }
    
    /**
     * Persists the Course.
     * 
     * Calls the EJB create() method and persists the Course to the DB
     * with its Lectures.
     */
    private void persistCourse() {
        courseFacade.create(course);
    }
    
    // GETTERS & SETTERS

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
