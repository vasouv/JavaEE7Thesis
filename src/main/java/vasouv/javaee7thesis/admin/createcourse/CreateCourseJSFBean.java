/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    CourseFacade courseFacade;
    
    @EJB
    LectureFacade lectureFacade;
    
    List<Lecture> lectures;
    Course course;
    int lectureID;
    
    @PostConstruct
    public void init() {
        lectureID = lectureFacade.findLectureMaxID();
        populateLectures();
    }
    
    public CreateCourseJSFBean() {
        this.lectures = new ArrayList();
        course = new Course();
//        lectureID = 0;
    }
    
    public void createCourse() {
        setCourseLecturesIDs();
        addLecturesToTheCourse();
        persistCourse();
    }
    
    private void populateLectures(){
        lectures.add(new Lecture());
        lectures.add(new Lecture());
        lectures.add(new Lecture());
    }
    
    private void setCourseLecturesIDs() {
        course.setIdcourse(courseFacade.findCourseMaxID() + 1);
        for(Lecture currentLec : lectures) {
            lectureID++;
            currentLec.setCourseid(course);
            currentLec.setIdlecture(lectureID);
        }
    }
    
    private void addLecturesToTheCourse(){
        course.setLecturesList(lectures);
    }
    
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
