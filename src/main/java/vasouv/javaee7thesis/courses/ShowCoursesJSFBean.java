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
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.courses.sessionbeans.LectureFacade;

/**
 *
 * @author vasouv
 */
@Named("showCoursesJSFBean")
public class ShowCoursesJSFBean implements Serializable {

    @EJB
    CourseFacade coursesFacade;
    
    @EJB
    LectureFacade lecturesFacade;
    
    List<Course> courses;
    List<Lecture> lectures;
    List<String> lecturesTitles;
    
    String title,description,image;
    String price;
    
    @PostConstruct
    public void init() {
        courses.addAll(coursesFacade.findAll());
        setTitle(courses.get(0).getTitle());
        setDescription(courses.get(0).getDescription());
        setImage(courses.get(0).getImage());
        setPrice("55");
        lectures.addAll(lecturesFacade.findLecturesByCourseName("hey"));
        lecturesTitles.add(lectures.get(0).getTitle());
//        lecturesTitles.addAll(lecturesFacade.findByCourseName());
//        lectures.addAll(lecturesFacade.findFuckingLectures("Developing Applications with NetBeans 8"));
//        setLectures(lecturesFacade.findFuckingLectures("Developing Applications with NetBeans 8"));
//        lecturesTitles.addAll(lecturesFacade.findByCourseName());
//        System.out.println(lectures.size());
//        lecturesTitles.add(lectures.get(0).getTitle());
//        lecturesTitles.add(lectures.get(1).getTitle());
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<String> getLecturesTitles() {
        return lecturesTitles;
    }

    public void setLecturesTitles(List<String> lecturesTitles) {
        this.lecturesTitles = lecturesTitles;
    }
    
    

}
