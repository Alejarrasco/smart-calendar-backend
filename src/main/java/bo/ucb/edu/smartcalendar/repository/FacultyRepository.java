package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    
    public Faculty findByFacultyName(String facultyName);
}
