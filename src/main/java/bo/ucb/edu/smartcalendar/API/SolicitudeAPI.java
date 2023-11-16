package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SolicitudeBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SolicitudeRequest;

@RestController
@RequestMapping(value = "api/v1/solicitude")
public class SolicitudeAPI {
    
    Logger LOGGER = LoggerFactory.getLogger(SolicitudeAPI.class);

    @Autowired
    private SolicitudeBl solicitudeBl;

    public SolicitudeAPI(SolicitudeBl solicitudeBl) {
        this.solicitudeBl = solicitudeBl;
    }


    @PostMapping()
    public SmartcalResponse CreateSolicitude(@RequestBody SolicitudeRequest solicitudeRequest){
        LOGGER.info("Create solicitude");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = solicitudeBl.CreateSolicitude(solicitudeRequest);
            response.setCode("SOLI-0001");
            
        } catch (Exception e) {
            response.setCode("SOLI-6001");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }
}
