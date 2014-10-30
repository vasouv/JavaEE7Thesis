/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import vasouv.javaee7thesis.courses.sessionbeans.CoursesFacade;
import vasouv.javaee7thesis.courses.sessionbeans.LecturesFacade;

/**
 *
 * @author vasouv
 */
@Named("showCoursesJSFBean")
public class ShowCoursesJSFBean implements Serializable {

    @EJB
    CoursesFacade coursesFacade;
    
    @EJB
    LecturesFacade lecturesFacade;
    
    List<Courses> courses;
    List<Lectures> lectures;
    List<String> lecturesTitles;
    
    String title,description,image;
    String price;
    
    @PostConstruct
    public void init() {
        courses.add(coursesFacade.findSingle());
        setTitle(courses.get(0).getTitle());
        setDescription(courses.get(0).getDescription());
        setImage(courses.get(0).getImage());
        setPrice("55");
        lecturesTitles.addAll(lecturesFacade.findByCourseName());
    }
    
    public ShowCoursesJSFBean() {
        this.courses = new ArrayList<>();
        this.title = "";
        this.description = "";
        this.image = "";
        this.price = "";
        this.lectures = new ArrayList<>();
        this.lecturesTitles = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public List<Lectures> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lectures> lectures) {
        this.lectures = lectures;
    }

    public List<String> getLecturesTitles() {
        return lecturesTitles;
    }

    public void setLecturesTitles(List<String> lecturesTitles) {
        this.lecturesTitles = lecturesTitles;
    }
    
    

}
