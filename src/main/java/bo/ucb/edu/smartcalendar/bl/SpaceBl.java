package bo.ucb.edu.smartcalendar.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Space;
import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
import bo.ucb.edu.smartcalendar.repository.SpaceRepository;

@Service
public class SpaceBl {

    Logger LOGGER = LoggerFactory.getLogger(SpaceBl.class);
    
    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private ScheduleBl scheduleBl;

    public SpaceBl(SpaceRepository spaceRepository, ScheduleBl scheduleBl) {
        this.spaceRepository = spaceRepository;
        this.scheduleBl = scheduleBl;
    }

    public SmartcalResponse ListSpacesGroupedByType(){
        SpaceType[] spaceTypes = SpaceType.values();
        Map<SpaceType, List<Space>> spacesGroupedByType = new HashMap<SpaceType, List<Space>>();
        LOGGER.info("Space types: " + spaceTypes);

        for(SpaceType spaceType : spaceTypes){
            LOGGER.info("Space type: " + spaceType);
            try { //FIXME jdbcMapping is null
                List<Space> spacesOfType = spaceRepository.findBySpaceType(spaceType);
                LOGGER.info("Spaces of type " + spaceType + ": " + spacesOfType);
                spacesGroupedByType.put(spaceType, spacesOfType);
            } catch (Exception e) {
                LOGGER.error("Error: " + e);
                spacesGroupedByType.put(spaceType, null);
            }
        }
        
        SmartcalResponse response = new SmartcalResponse();
        response.setData(spacesGroupedByType);
        return response;
    }

    public SmartcalResponse FindSpaceById(Integer id) throws RuntimeException{
        Space space = spaceRepository.findById(id).orElse(null);
        if (space == null) {
            LOGGER.error("Space not found");
            throw new RuntimeException("Space not found");
        }
        LOGGER.info("Space: " + space);
        SmartcalResponse response = new SmartcalResponse();
        response.setData(space);
        return response;
    }

    public SmartcalResponse CloseSpace(Integer space_id) throws RuntimeException{
        Space space = spaceRepository.findById(space_id).orElse(null);
        if (space == null) {
            LOGGER.error("Space not found");
            throw new RuntimeException("Space not found");
        }
        try {
            scheduleBl.CloseScheduleBySpaceId(space_id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        space.setSpaceStatus(Space.SpaceStatus.CLOSED);
        spaceRepository.save(space);
        LOGGER.info("Space: " + space);
        SmartcalResponse response = new SmartcalResponse();
        response.setData(space);
        return response;
    }
}
