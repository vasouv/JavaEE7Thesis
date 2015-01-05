package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vasouv
 */
@Entity
@Table(name = "LECTURES")
@NamedQueries({
    @NamedQuery(name = "Lectures.findAll", query = "SELECT l FROM Lecture l"),
    @NamedQuery(name = "Lectures.findByIdlecture", query = "SELECT l FROM Lecture l WHERE l.idlecture = :idlecture"),
    @NamedQuery(name = "Lectures.findByTitle", query = "SELECT l FROM Lecture l WHERE l.title = :title"),
    @NamedQuery(name = "Lectures.findByYoutube", query = "SELECT l FROM Lecture l WHERE l.youtube = :youtube"),
    @NamedQuery(name = "Lectures.findByMaterial", query = "SELECT l FROM Lecture l WHERE l.material = :material")})
public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLECTURE")
    private Integer idlecture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "YOUTUBE")
    private String youtube;
    @Size(max = 200)
    @Column(name = "MATERIAL")
    private String material;
    
    @JoinColumn(name = "COURSEID", referencedColumnName = "IDCOURSE")
    @ManyToOne(optional = false)
    private Course courseid;

    public Lecture() {
    }

    public Lecture(Integer idlecture) {
        this.idlecture = idlecture;
    }

    public Lecture(Integer idlecture, String title, String youtube) {
        this.idlecture = idlecture;
        this.title = title;
        this.youtube = youtube;
    }

    public Integer getIdlecture() {
        return idlecture;
    }

    public void setIdlecture(Integer idlecture) {
        this.idlecture = idlecture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Course getCourseid() {
        return courseid;
    }

    public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlecture != null ? idlecture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
        if ((this.idlecture == null && other.idlecture != null) || (this.idlecture != null && !this.idlecture.equals(other.idlecture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vasouv.javaee7thesis.courses.Lectures[ idlecture=" + idlecture + " ]";
    }
    
}
