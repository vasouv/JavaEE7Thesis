/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vasouv
 */
@Entity
@Table(name = "COURSES")
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Courses.findByIdcourse", query = "SELECT c FROM Course c WHERE c.idcourse = :idcourse"),
    @NamedQuery(name = "Courses.findByTitle", query = "SELECT c FROM Course c WHERE c.title = :title"),
    @NamedQuery(name = "Courses.findByPrice", query = "SELECT c FROM Course c WHERE c.price = :price"),
    @NamedQuery(name = "Courses.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOURSE")
    private Integer idcourse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "IMAGE")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseid")
    private List<Lecture> lecturesList;

    public Course() {
    }

    public Course(Integer idcourse) {
        this.idcourse = idcourse;
    }

    public Course(Integer idcourse, String title, int price, String image) {
        this.idcourse = idcourse;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Integer getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(Integer idcourse) {
        this.idcourse = idcourse;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Lecture> getLecturesList() {
        return lecturesList;
    }

    public void setLecturesList(List<Lecture> lecturesList) {
        this.lecturesList = lecturesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcourse != null ? idcourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.idcourse == null && other.idcourse != null) || (this.idcourse != null && !this.idcourse.equals(other.idcourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vasouv.javaee7thesis.courses.Courses[ idcourse=" + idcourse + " ]";
    }
    
}
