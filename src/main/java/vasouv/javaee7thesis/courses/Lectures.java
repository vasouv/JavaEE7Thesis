/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Lectures.findAll", query = "SELECT l FROM Lectures l"),
    @NamedQuery(name = "Lectures.findById", query = "SELECT l FROM Lectures l WHERE l.id = :id"),
    @NamedQuery(name = "Lectures.findByTitle", query = "SELECT l FROM Lectures l WHERE l.title = :title"),
    @NamedQuery(name = "Lectures.findByYoutube", query = "SELECT l FROM Lectures l WHERE l.youtube = :youtube"),
    @NamedQuery(name = "Lectures.findByMaterial", query = "SELECT l FROM Lectures l WHERE l.material = :material")})
public class Lectures implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
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
//    @ManyToMany(mappedBy = "lecturesList")
//    private List<Courses> coursesList;
    @ManyToOne
    @JoinTable(name="COURSELECTURES")
    private Courses course;

    public Lectures() {
    }

    public Lectures(Integer id) {
        this.id = id;
    }

    public Lectures(Integer id, String title, String youtube) {
        this.id = id;
        this.title = title;
        this.youtube = youtube;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public List<Courses> getCoursesList() {
//        return coursesList;
//    }
//
//    public void setCoursesList(List<Courses> coursesList) {
//        this.coursesList = coursesList;
//    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lectures)) {
            return false;
        }
        Lectures other = (Lectures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vasouv.javaee7thesis.courses.Lectures[ id=" + id + " ]";
    }
    
}
