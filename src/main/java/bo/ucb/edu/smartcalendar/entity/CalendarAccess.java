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
@Table(name = "calendar_access")
public class CalendarAccess {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_access_id")
    private Integer calendarAccessId;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

    @Column(columnDefinition = "BIT(1) DEFAULT 1",name = "calendar_access_status", nullable = false)
    private boolean calendarAccessStatus = true;

    //Aud fields
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "aud_date", nullable = false)
    private Date audDate = new Date(System.currentTimeMillis());

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'localhost'",name = "aud_host", nullable = false)
    private String audHost = "localhost";

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'springuser'",name = "aud_user", nullable = false, length = 100)
    private String audUser = "springuser";


    // Constructor de la clase CalendarAccess.java
    public CalendarAccess() {
    }


    public Integer getCalendarAccessId() {
        return calendarAccessId;
    }


    public void setCalendarAccessId(Integer calendarAccessId) {
        this.calendarAccessId = calendarAccessId;
    }


    public Space getSpace() {
        return space;
    }


    public void setSpace(Space space) {
        this.space = space;
    }


    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    public String getRegisteredDate() {
        return registeredDate;
    }


    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public boolean isCalendarAccessStatus() {
        return calendarAccessStatus;
    }

    public void setCalendarAccessStatus(boolean calendarAccessStatus) {
        this.calendarAccessStatus = calendarAccessStatus;
    }
}
