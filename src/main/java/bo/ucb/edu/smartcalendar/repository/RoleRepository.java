package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
