package bo.ucb.edu.smartcalendar.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitude")
public class Solicitude {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitude_id")
    private int solicitudeId;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "solicitude_status", nullable = false)
    private SolicitudeStatus solicitudeStatus;

    @Column(name = "recurrent", nullable = false)
    private boolean recurrent;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @OneToMany(mappedBy = "solicitude")
    private Set<Assignation> assignations;


    // Constructor de la clase Solicitude.java
    public Solicitude() {
    }


    public int getsolicitudeId() {
        return solicitudeId;
    }


    public void setsolicitudeId(int solicitudeId) {
        this.solicitudeId = solicitudeId;
    }


    public Subject getSubject() {
        return subject;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    public Date getRegisterDate() {
        return registerDate;
    }


    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }


    public SolicitudeStatus getsolicitudeStatus() {
        return solicitudeStatus;
    }


    public void setSolicitudeStatus(SolicitudeStatus solicitudeStatus) {
        this.solicitudeStatus = solicitudeStatus;
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


    //enum para el estado de la solicitud
    public enum SolicitudeStatus {
        PENDING("Pendiente"),
        APPROVED("Aprobada"),
        PAST("Pasada"),
        REJECTED("Rechazada"),
        CANCELED("Cancelada");

        private String status;

        private SolicitudeStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public static SolicitudeStatus fromStatus(String status) {
            for (SolicitudeStatus s : SolicitudeStatus.values()) {
                if (s.getStatus().equals(status)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No se encontró el estado de la solicitud");
        }
    }
}
