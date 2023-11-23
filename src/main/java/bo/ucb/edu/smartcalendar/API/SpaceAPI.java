package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SpaceBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(value = "api/v1/space")
public class SpaceAPI {

    Logger LOGGER = LoggerFactory.getLogger(SpaceAPI.class);
    
    @Autowired
    private SpaceBl spaceBl;

    public SpaceAPI(SpaceBl spaceBl) {
        this.spaceBl = spaceBl;
    }

    @GetMapping(path = "/list")
    public SmartcalResponse ListSpacesGroupedByType(){
        LOGGER.info("Called ListSpacesGroupedByType");
        SmartcalResponse response = spaceBl.ListSpacesGroupedByType();
        response.setCode("SPAC-0000");
        return response;
    }

    @GetMapping(path = "/{id}")
    public SmartcalResponse FindSpaceById(Integer id){
        LOGGER.info("Called FindSpaceById");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = spaceBl.FindSpaceById(id);
            response.setCode("SPAC-0001");
        } catch (RuntimeException e) {
            response.setCode("SPAC-6001");
            response.setErrormessage("No se encontró el espacio especificado");
        }
        return response;
    }

    @PutMapping("{space_id}/closed")
    public SmartcalResponse CloseSpace(Integer space_id){
        LOGGER.info("Called CloseSpace");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = spaceBl.CloseSpace(space_id);
            response.setCode("SPAC-0002");
        } catch (RuntimeException e) {
            response.setCode("SPAC-6002");
            response.setErrormessage("No se pudo cerrar el espacio");
        }
        return response;
    }
}
