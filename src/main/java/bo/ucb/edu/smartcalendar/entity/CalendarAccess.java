package bo.ucb.edu.smartcalendar.entity;

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


}
