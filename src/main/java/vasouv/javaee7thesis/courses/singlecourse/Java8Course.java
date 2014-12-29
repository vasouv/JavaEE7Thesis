/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.singlecourse;

import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vasouv
 */
@Named("java8Course")
@RequestScoped
public class Java8Course extends AbstractCourse implements Serializable {

    private StreamedContent file;
    
    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }
    
    public Java8Course() {
        super();
        setCourseName("Java8");
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/courses/java8/java8Additional.zip");
        file = new DefaultStreamedContent(stream, "application/zip", "java8Additional.zip");
    }

    // ----- GETTERS & SETTERS -----
    
    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
}
