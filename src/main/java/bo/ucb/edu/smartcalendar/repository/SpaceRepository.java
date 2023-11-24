package bo.ucb.edu.smartcalendar.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Space;

public interface SpaceRepository extends JpaRepository<Space, Integer> {

    @Query(value = "SELECT * FROM space WHERE space_type = ?1 AND space_status = 1", nativeQuery = true)
    public List<Space> findBySpaceType(Space.SpaceType spaceType);

    @Query(value = "SELECT * FROM space WHERE space_id = ?1 AND space_status = 1", nativeQuery = true)
    public Space findBySpaceId(Integer spaceId);
}
