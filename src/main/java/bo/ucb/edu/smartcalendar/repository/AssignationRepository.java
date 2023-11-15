package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Assignation;

public interface AssignationRepository extends JpaRepository<Assignation, Integer> {
}
