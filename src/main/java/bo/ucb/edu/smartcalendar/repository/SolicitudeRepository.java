package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Solicitude;

public interface SolicitudeRepository extends JpaRepository<Solicitude, Integer> {
    
    @Query(value = "SELECT * FROM solicitude WHERE person_id = ?1", nativeQuery = true)
    List<Solicitude> findByPersonId(Integer personId);

    @Query(value = "SELECT * FROM solicitude WHERE solicitude_status = 'PENDING'", nativeQuery = true)
    List<Solicitude> findBySolicitudeStatusPending();

    @Query(value = "SELECT * FROM solicitude WHERE solicitude_status = 'APPROVED'", nativeQuery = true)
    List<Solicitude> findBySolicitudeStatusApproved();

    @Query(value = "SELECT * FROM solicitude WHERE solicitude_status = 'REJECTED'", nativeQuery = true)
    List<Solicitude> findBySolicitudeStatusRejected();

    Solicitude findBySolicitudeId(Integer solicitudeId);
}
