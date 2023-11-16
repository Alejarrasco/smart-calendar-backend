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

    Logger LOGGER = LoggerFactory.getLogger(PlanificationViewBl.class);
    
    @Autowired
    private SpaceRepository spaceRepository;

    public SpaceBl(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public SmartcalResponse ListSpacesGroupedByType(){
        SpaceType[] spaceTypes = SpaceType.values();
        Map<SpaceType, List<Space>> spacesGroupedByType = new HashMap<SpaceType, List<Space>>();
        LOGGER.info("Space types: " + spaceTypes);

        for(SpaceType spaceType : spaceTypes){
            List<Space> spacesOfType = spaceRepository.findBySpaceType(spaceType);
            LOGGER.info("Spaces of type " + spaceType + ": " + spacesOfType);
            spacesGroupedByType.put(spaceType, spacesOfType);
        }
        
        SmartcalResponse response = new SmartcalResponse();
        response.setData(spacesGroupedByType);
        return response;
    }
}
