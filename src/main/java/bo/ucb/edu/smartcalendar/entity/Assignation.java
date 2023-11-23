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
    @JoinColumn(name = "solicitude_id", nullable = false)
    private Solicitude solicitude;

    @Column(name = "approve_date", nullable = true)
    private Date approveDate;

    @Column(name = "ai_generated", nullable = false)
    private boolean aiGenerated;

    @Column(columnDefinition = "BIT(1) DEFAULT 1",name = "assignation_status", nullable = false)
    private boolean assignationStatus;

    //Aud fields
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "aud_date", nullable = false)
    private Date audDate;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'localhost'",name = "aud_host", nullable = false)
    private String audHost;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'springuser'",name = "aud_user", nullable = false, length = 100)
    private String audUser;


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


    public Solicitude getsolicitude() {
        return solicitude;
    }


    public void setsolicitude(Solicitude solicitude) {
        this.solicitude = solicitude;
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

    public boolean isAssignationStatus() {
        return assignationStatus;
    }

    public void setAssignationStatus(boolean assignationStatus) {
        this.assignationStatus = assignationStatus;
    }

    
}
