package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SpaceBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SpaceRequest;

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
    public SmartcalResponse FindSpaceById(@PathVariable Integer id){
        LOGGER.info("Called FindSpaceById");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = spaceBl.FindSpaceById(id);
            response.setCode("SPAC-0001");
        } catch (RuntimeException e) {
            response.setCode("SPAC-6001");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

    @PostMapping
    public SmartcalResponse CreateSpace(@RequestBody SpaceRequest spaceRequest){
        LOGGER.info("Called CreateSpace");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = spaceBl.CreateSpace(spaceRequest);
            response.setCode("SPAC-0002");
        } catch (RuntimeException e) {
            response.setCode("SPAC-6002");
            response.setErrormessage("No se pudo crear el espacio");
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

    @GetMapping(path = "/type/list")
    public SmartcalResponse ListSpaceTypes(){
        LOGGER.info("Called ListSpaceTypes");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = spaceBl.ListSpaceTypes();
            response.setCode("SPAC-0003");
        } catch (RuntimeException e) {
            response.setCode("SPAC-6003");
            response.setErrormessage("No se pudo listar los tipos de espacio");
        }
        return response;
    }
}
