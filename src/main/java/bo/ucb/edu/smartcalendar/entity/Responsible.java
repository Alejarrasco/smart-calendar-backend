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
@Table(name = "responsible")
public class Responsible {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "responsible_id")
    private int responsibleId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(columnDefinition = "BIT(1) DEFAULT 1",name = "responsible_status", nullable = false)
    private boolean responsibleStatus = true;

    //Aud fields
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "aud_date", nullable = false)
    private Date audDate = new Date(System.currentTimeMillis());

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'localhost'",name = "aud_host", nullable = false)
    private String audHost = "localhost";

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'springuser'",name = "aud_user", nullable = false, length = 100)
    private String audUser = "springuser";


    // Constructor de la clase Responsible.java
    public Responsible() {
    }


    public int getResponsibleId() {
        return responsibleId;
    }


    public void setResponsibleId(int responsibleId) {
        this.responsibleId = responsibleId;
    }


    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    public Subject getSubject() {
        return subject;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isResponsibleStatus() {
        return responsibleStatus;
    }

    public void setResponsibleStatus(boolean responsibleStatus) {
        this.responsibleStatus = responsibleStatus;
    }

    @Override
    public String toString() {
        return "Responsible: "+person.getFirstName()+" "+person.getLastName()+" on "+subject.getSubjectName();
    }
}
