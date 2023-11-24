package bo.ucb.edu.smartcalendar.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectId;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "subject_name", length = 100, nullable = false)
    private String subjectName;

    @Column(name = "subject_description", length = 400, nullable = false)
    private String subjectDescription;

    @Column(name = "subject_code", length = 50, nullable = false, unique = true)
    private String subjectCode;

    @Column(columnDefinition = "BIT(1) DEFAULT 1",name = "subject_status", nullable = false)
    private boolean subjectStatus = true;

    @OneToMany(mappedBy = "subject")
    private Set<Responsible> responsibles;

    @OneToMany(mappedBy = "subject")
    private Set<Solicitude> solicitudes;

    //Aud fields
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "aud_date")
    private Date audDate = new Date(System.currentTimeMillis());

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'localhost'",name = "aud_host", nullable = false)
    private String audHost = "localhost";

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'springuser'",name = "aud_user", nullable = false)
    private String audUser = "springuser";


    // Constructor de la clase Subject.java
    public Subject() {
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Set<Responsible> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(Set<Responsible> responsibles) {
        this.responsibles = responsibles;
    }

    public Set<Solicitude> getSolicitudes() {
        return solicitudes;
    }

    public boolean isSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(boolean subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

}
