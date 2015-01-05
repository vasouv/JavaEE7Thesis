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
@Named("webServicesCourse")
@RequestScoped
public class WebServicesCourse extends AbstractCourse implements Serializable {

    private StreamedContent file;
    
    @PostConstruct
    public void init() {
        username = auth.getUser().getUsername();
        courseLectures.addAll(lectureFacade.findLecturesByCourseName(courseName));
    }
    
    public WebServicesCourse() {
        super();
        setCourseName("Web Services");
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/courses/webservices/webservicesAdditional.zip");
        file = new DefaultStreamedContent(stream, "application/zip", "webservicesAdditional.zip");
    }
    
    // ----- GETTERS & SETTERS -----

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
}
