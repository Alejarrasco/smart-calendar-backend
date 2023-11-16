package bo.ucb.edu.smartcalendar.entity;

import java.sql.Date;
import java.sql.Time;

import org.hibernate.annotations.Immutable;

import bo.ucb.edu.smartcalendar.entity.Period.Weekday;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity(name = "planification")
@Immutable
public class Planification {

    @Id
    @Column(name = "assignation_id")
    private int assignationId;

    @Column(name = "space_id")
    private int spaceId;

    @Column(name = "space_name")
    private String spaceName;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "recurrent")
    private boolean recurrent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "period_id")
    private int periodId;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private Weekday weekDay;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;


    // Constructor de la clase Planification.java
    public Planification() {
    }


    public int getAssignationId() {
        return assignationId;
    }


    public void setAssignationId(int assignationId) {
        this.assignationId = assignationId;
    }


    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean isRecurrent() {
        return recurrent;
    }


    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public int getPeriodId() {
        return periodId;
    }


    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }


    public Weekday getWeekDay() {
        return weekDay;
    }


    public void setWeekDay(Weekday weekDay) {
        this.weekDay = weekDay;
    }


    public Time getStartTime() {
        return startTime;
    }


    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }


    public Time getEndTime() {
        return endTime;
    }


    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

}
