package bo.ucb.edu.smartcalendar.entity;

import java.util.Set;

import java.sql.Date;

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
@Table(name = "schedule")
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period;

    @Column(name = "open_date", nullable = false)
    private Date openDate;

    @Column(name = "close_date", nullable = false)
    private Date closeDate;

    @Column(columnDefinition = "bit(1) DEFAULT 1",name = "schedule_status", nullable = false)
    private boolean scheduleStatus = true;

    @OneToMany(mappedBy = "schedule")
    private Set<Assignation> assignations;

    //Aud fields
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "aud_date", nullable = false)
    private Date audDate = new Date(System.currentTimeMillis());

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'localhost'",name = "aud_host", nullable = false)
    private String audHost = "localhost";

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'springuser'",name = "aud_user", nullable = false, length = 100)
    private String audUser = "springuser";


    // Constructor de la clase Schedule.java
    public Schedule() {
    }


    public int getScheduleId() {
        return scheduleId;
    }


    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }


    public Space getSpace() {
        return space;
    }


    public void setSpace(Space space) {
        this.space = space;
    }


    public Period getPeriod() {
        return period;
    }


    public void setPeriod(Period period) {
        this.period = period;
    }


    public Date getOpenDate() {
        return openDate;
    }


    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }


    public Date getCloseDate() {
        return closeDate;
    }


    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public boolean isScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(boolean scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
