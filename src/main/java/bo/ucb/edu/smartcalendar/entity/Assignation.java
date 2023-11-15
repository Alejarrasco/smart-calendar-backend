package bo.ucb.edu.smartcalendar.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assignation")
public class Assignation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignation_id")
    private int assignationId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "solitude_id", nullable = false)
    private Solicitude solitude;

    @Column(name = "approve_date", nullable = true)
    private Date approveDate;

    @Column(name = "ai_generated", nullable = false)
    private boolean aiGenerated;


    // Constructor de la clase Assignation.java
    public Assignation() {
    }


    public int getAssignationId() {
        return assignationId;
    }


    public void setAssignationId(int assignationId) {
        this.assignationId = assignationId;
    }


    public Schedule getSchedule() {
        return schedule;
    }


    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public Solicitude getSolitude() {
        return solitude;
    }


    public void setSolitude(Solicitude solitude) {
        this.solitude = solitude;
    }


    public Date getApproveDate() {
        return approveDate;
    }


    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }


    public boolean isAiGenerated() {
        return aiGenerated;
    }


    public void setAiGenerated(boolean aiGenerated) {
        this.aiGenerated = aiGenerated;
    }

    
}
