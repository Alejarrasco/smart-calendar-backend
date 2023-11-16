package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person findByPersonId(Integer id);
}
