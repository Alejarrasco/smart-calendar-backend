package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    
}
