package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
    
}
