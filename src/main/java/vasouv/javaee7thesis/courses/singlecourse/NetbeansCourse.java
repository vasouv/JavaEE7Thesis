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
@Named("netbeansCourse")
@RequestScoped
public class NetbeansCourse extends AbstractCourse implements Serializable {
    
    private StreamedContent file;
    
    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }

    public NetbeansCourse() {
        super();
        setCourseName("Developing Applications with NetBeans 8");
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/courses/netbeans/netbeansAdditional.zip");
        file = new DefaultStreamedContent(stream, "application/zip", "netbeansAdditional.zip");
    }
    
    // ----- GETTERS & SETTERS -----

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
}
