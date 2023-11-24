package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.PersonBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(value = "api/v1/person")
public class PersonAPI {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PersonAPI.class);
    
    @Autowired
    private PersonBl personBl;

    public PersonAPI(PersonBl personBl) {
        this.personBl = personBl;
    }

    @GetMapping
    public SmartcalResponse ListPersons(){
        LOGGER.info("Called ListPersons");
        return personBl.ListPersons();
    }
}
