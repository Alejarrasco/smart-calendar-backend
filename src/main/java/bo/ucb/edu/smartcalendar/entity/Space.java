package bo.ucb.edu.smartcalendar.entity;

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
@Table(name = "space")
public class Space {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")
    private int spaceId;

    @Column(name = "space_name", length = 50, nullable = false)
    private String spaceName;

    @Column(name = "space_description", length = 400, nullable = false)
    private String spaceDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_status", nullable = false)
    private SpaceStatus spaceStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_type", nullable = false)
    private SpaceType spaceType;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "space")
    private Set<Schedule> schedules;


    // Constructor de la clase Space.java
    public Space() {
    }


    public int getSpaceId() {
        return spaceId;
    }


    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }


    public String getSpaceName() {
        return spaceName;
    }


    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }


    public String getSpaceDescription() {
        return spaceDescription;
    }


    public void setSpaceDescription(String spaceDescription) {
        this.spaceDescription = spaceDescription;
    }


    public SpaceStatus getSpaceStatus() {
        return spaceStatus;
    }


    public void setSpaceStatus(SpaceStatus spaceStatus) {
        this.spaceStatus = spaceStatus;
    }


    public SpaceType getSpaceType() {
        return spaceType;
    }


    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }


    public int getCapacity() {
        return capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    //Enums para el estado del espacio y el tipo de espacio
    public enum SpaceStatus {
        //Espacios
        //ABIERTO
        //CERRADO
        //ELIMINADO

        OPEN("Abierto"),
        CLOSED("Cerrado"),
        DELETED("Eliminado");

        private final String displayName;

        SpaceStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum SpaceType {
        //Espacios:
        //AULA
        //LABORATORIO
        //AUDITORIO
        
        CLASSROOM("Aula"),
        LABORATORY("Laboratorio"),
        AUDITORIUM("Auditorio");

        private final String displayName;

        SpaceType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
