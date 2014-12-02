/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.singlecourse;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author vasouv
 */
@Named("javaEE7Course")
@RequestScoped
public class JavaEE7Course extends AbstractCourse implements Serializable {

    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }

    public JavaEE7Course() {
        super();
        setCourseName("Learning Java EE 7");
    }

}
