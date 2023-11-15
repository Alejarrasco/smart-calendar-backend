package bo.ucb.edu.smartcalendar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SpaceBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(value = "api/v1/space")
public class SpaceAPI {
    
    @Autowired
    private SpaceBl spaceBl;

    public SpaceAPI(SpaceBl spaceBl) {
        this.spaceBl = spaceBl;
    }

    @GetMapping(path = "/list")
    public SmartcalResponse ListSpacesGroupedByType(){
        SmartcalResponse response = spaceBl.ListSpacesGroupedByType();
        response.setCode("SPAC-0000");
        return response;
    }
}
