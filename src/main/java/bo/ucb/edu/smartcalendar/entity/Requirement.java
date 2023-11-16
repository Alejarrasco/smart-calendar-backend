package bo.ucb.edu.smartcalendar.entity;

import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "requirement")
public class Requirement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Integer requirementId;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "periods_per_class", nullable = false)
    private Integer periodsPerClass;

    @Column(name = "classes_per_week", nullable = false)
    private Integer classesPerWeek;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_type", nullable = false)
    private SpaceType spaceType;

    @Column(name = "max_alumni", nullable = false)
    private Integer maxAlumni;

    @Column(name = "semester", nullable = false)
    private String semester;


    // Constructor de la clase Requirement.java
    public Requirement() {
    }


    public Integer getRequirementId() {
        return requirementId;
    }


    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }


    public Subject getSubject() {
        return subject;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public Integer getPeriodsPerClass() {
        return periodsPerClass;
    }


    public void setPeriodsPerClass(Integer periodsPerClass) {
        this.periodsPerClass = periodsPerClass;
    }


    public Integer getClassesPerWeek() {
        return classesPerWeek;
    }


    public void setClassesPerWeek(Integer classesPerWeek) {
        this.classesPerWeek = classesPerWeek;
    }


    public SpaceType getSpaceType() {
        return spaceType;
    }


    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }


    public Integer getMaxAlumni() {
        return maxAlumni;
    }


    public void setMaxAlumni(Integer maxAlumni) {
        this.maxAlumni = maxAlumni;
    }


    public String getSemester() {
        return semester;
    }


    public void setSemester(String semester) {
        this.semester = semester;
    }

}
