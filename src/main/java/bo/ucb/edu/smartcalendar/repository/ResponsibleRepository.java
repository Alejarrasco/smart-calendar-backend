package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Responsible;

public interface ResponsibleRepository extends JpaRepository<Responsible, Integer> {
    
    @Query(value = "SELECT * FROM responsible WHERE subject_id = ?1", nativeQuery = true)
    List<Responsible> findBySubjectId(Integer subjectId);
}
