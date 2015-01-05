package vasouv.javaee7thesis.courses.singlecourse;

import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vasouv
 */
@Named("javaEE7Course")
@RequestScoped
public class JavaEE7Course extends AbstractCourse implements Serializable {

    private StreamedContent file;

    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }

    public JavaEE7Course() {
        super();
        setCourseName("Learning Java EE 7");
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/courses/javaee7/javaee7material.zip");
        file = new DefaultStreamedContent(stream, "application/zip", "javaee7material.zip");
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

}
