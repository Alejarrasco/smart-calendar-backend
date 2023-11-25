package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping() //FIXME is returning a StackOverflowError
    public SmartcalResponse GetSolicitudes(@RequestHeader Integer token){
        LOGGER.info("Get solicitudes");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = solicitudeBl.GetSolicitudes(token);
            response.setCode("SOLI-0000");
        } catch (Exception e) {
            response.setCode("SOLI-6000");
            response.setErrormessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
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
            e.printStackTrace();
        }
        return response;
    }

    @PutMapping("/{solicitudeId}/approve")
    public SmartcalResponse ApproveSolicitude(@RequestHeader Integer token, @RequestBody Integer solicitudeId){
        LOGGER.info("Approve solicitude");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = solicitudeBl.ApproveSolicitude(token, solicitudeId);
            response.setCode("SOLI-0002");
            
        } catch (Exception e) {
            response.setCode("SOLI-6002");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{solicitudeId}/reject")
    public SmartcalResponse RejectSolicitude(@RequestHeader Integer token, @RequestBody Integer solicitudeId){
        LOGGER.info("Reject solicitude");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = solicitudeBl.RejectSolicitude(token, solicitudeId);
            response.setCode("SOLI-0003");
            
        } catch (Exception e) {
            response.setCode("SOLI-6003");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{solicitudeId}/cancel")
    public SmartcalResponse CancelSolicitude(@RequestHeader Integer token, @RequestBody Integer solicitudeId){
        LOGGER.info("Cancel solicitude");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = solicitudeBl.CancelSolicitude(token, solicitudeId);
            response.setCode("SOLI-0004");
            
        } catch (Exception e) {
            response.setCode("SOLI-6004");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }
}
