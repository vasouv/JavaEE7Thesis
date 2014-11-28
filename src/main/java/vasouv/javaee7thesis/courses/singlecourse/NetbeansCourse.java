/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.singlecourse;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author vasouv
 */
@Named("netbeansCourse")
@RequestScoped
public class NetbeansCourse extends AbstractCourse implements Serializable {
    
    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }

    public NetbeansCourse() {
        super();
        setCourseName("Developing Applications with NetBeans 8");
    }
    
}
