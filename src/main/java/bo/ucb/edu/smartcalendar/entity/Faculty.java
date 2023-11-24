package bo.ucb.edu.smartcalendar.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private int facultyId;

    @Column(name = "faculty_name", length = 100, nullable = false, unique = true)
    private String facultyName;

    @OneToMany(mappedBy = "faculty")
    private Set<Subject> subjects;


    // Constructor de la clase Faculty.java
    public Faculty() {
    }

    public int getFacultyId() {
        return facultyId;
    }


    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }


    public String getFacultyName() {
        return facultyName;
    }


    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    
}
