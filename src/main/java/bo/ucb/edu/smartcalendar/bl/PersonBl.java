package bo.ucb.edu.smartcalendar.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.api.SubjectAPI;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Responsible;
import bo.ucb.edu.smartcalendar.entity.Subject;
import bo.ucb.edu.smartcalendar.repository.GroupRepository;
import bo.ucb.edu.smartcalendar.repository.PersonRepository;
import bo.ucb.edu.smartcalendar.repository.ResponsibleRepository;
import bo.ucb.edu.smartcalendar.repository.RoleRepository;

@Service
public class PersonBl {
    
    Logger LOGGER = LoggerFactory.getLogger(PersonBl.class);
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoleRepository roleRepository;

    public PersonBl(PersonRepository personRepository, ResponsibleRepository responsibleRepository, GroupRepository groupRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.responsibleRepository = responsibleRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
    }

    public SmartcalResponse ListPersons(){
        LOGGER.info("Called ListPersons");
        SmartcalResponse response = new SmartcalResponse();
        response.setData(personRepository.findAll());
        return response;
    }


    //TODO
    // public SmartcalResponse ListResponsibles(){}
    

    public void CreateResponsibles(Integer[] personsIds, Subject subject){
        LOGGER.info("Called CreateResponsibles");
        for (Integer personId : personsIds) {
            Responsible responsible = new Responsible();
            LOGGER.info("Looking for person with id: " + personId);
            responsible.setPerson(personRepository.findById(personId).get());
            responsible.setSubject(subject);
            responsibleRepository.save(responsible);
            LOGGER.info("Assigned responsibility: " + responsible);
        }
    }
}
