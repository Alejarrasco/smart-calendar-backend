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
@Table(name = "solitude")
public class Solicitude {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solitude_id")
    private int solitudeId;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "solitude_status", nullable = false)
    private SolitudeStatus solitudeStatus;

    @Column(name = "recurrent", nullable = false)
    private boolean recurrent;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @OneToMany(mappedBy = "solitude")
    private Set<Assignation> assignations;


    // Constructor de la clase Solicitude.java
    public Solicitude() {
    }


    public int getSolitudeId() {
        return solitudeId;
    }


    public void setSolitudeId(int solitudeId) {
        this.solitudeId = solitudeId;
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


    public SolitudeStatus getSolitudeStatus() {
        return solitudeStatus;
    }


    public void setSolitudeStatus(SolitudeStatus solitudeStatus) {
        this.solitudeStatus = solitudeStatus;
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
    public enum SolitudeStatus {
        PENDING("Pendiente"),
        APPROVED("Aprobada"),
        PAST("Pasada"),
        REJECTED("Rechazada"),
        CANCELED("Cancelada");

        private String status;

        private SolitudeStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public static SolitudeStatus fromStatus(String status) {
            for (SolitudeStatus s : SolitudeStatus.values()) {
                if (s.getStatus().equals(status)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No se encontró el estado de la solicitud");
        }
    }
}
