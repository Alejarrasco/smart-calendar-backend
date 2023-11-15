package bo.ucb.edu.smartcalendar.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.entity.Space;
import bo.ucb.edu.smartcalendar.repository.SpaceRepository;

@Service
public class SpaceBl {
    
    @Autowired
    private SpaceRepository spaceRepository;

    public SpaceBl(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Space getSpaceById(Integer spaceId) {
        return spaceRepository.findById(spaceId).orElse(null);
    }

    public Space saveSpace(Space space) {
        return spaceRepository.save(space);
    }
}
