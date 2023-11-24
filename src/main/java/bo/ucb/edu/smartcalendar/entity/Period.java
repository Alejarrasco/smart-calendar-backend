package bo.ucb.edu.smartcalendar.entity;

import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "period")
public class Period {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "period_id")
    private int periodId;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday", nullable = false)
    private Weekday weekday;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @OneToMany(mappedBy = "period")
    private Set<Schedule> schedules;


    // Constructor de la clase Period.java
    public Period() {
    }


    public int getPeriodId() {
        return periodId;
    }


    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }


    public Weekday getWeekday() {
        return weekday;
    }


    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
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

    
    //Enum para los dias de la semana
    public enum Weekday {
        MON("Lunes"),
        TUE("Martes"),
        WED("Miercoles"),
        THU("Jueves"),
        FRI("Viernes"),
        SAT("Sabado"),
        SUN("Domingo");

        private String weekday;

        Weekday(String weekday) {
            this.weekday = weekday;
        }

        public String getWeekday() {
            return weekday;
        }

        public static Weekday fromString(String text) {
            for (Weekday b : Weekday.values()) {
                if (b.weekday.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return weekday;
        }
    }
}
