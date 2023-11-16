package bo.ucb.edu.smartcalendar.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @ManyToMany
    @JoinTable(
        name = "user_group",
        joinColumns = @jakarta.persistence.JoinColumn(name = "person_id"),
        inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "group_id")
    )

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @OneToMany(mappedBy = "person")
    private Set<Solicitude> solicitudes;

    @OneToMany(mappedBy = "person")
    private Set<Responsible> responsibles;


    // Constructor de la clase Person.java
    public Person() {
    }


    public int getPersonId() {
        return personId;
    }


    public void setPersonId(int personId) {
        this.personId = personId;
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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}
