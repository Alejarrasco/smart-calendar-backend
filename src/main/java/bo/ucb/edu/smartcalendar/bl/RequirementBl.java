package bo.ucb.edu.smartcalendar.bl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.RequirementRequest;
import bo.ucb.edu.smartcalendar.entity.Requirement;
import bo.ucb.edu.smartcalendar.entity.Subject;
import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
import bo.ucb.edu.smartcalendar.repository.RequirementRepository;

@Service
public class RequirementBl {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RequirementBl.class);
    
    @Autowired
    private RequirementRepository requirementRepository;

    public RequirementBl(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    //TODO
    // public SmartcalResponse ListRequirements(){}

    public void CreateRequirements(RequirementRequest[] requirements, Subject subject){
        LOGGER.info("Called CreateRequirements");
        for(RequirementRequest requirementRequest : requirements){
            Requirement requirement = new Requirement();
            requirement.setSubject(subject);
            requirement.setPeriodsPerClass(requirementRequest.getPeriodsPerClass());
            requirement.setClassesPerWeek(requirementRequest.getClassesPerWeek());
            requirement.setSpaceType(SpaceType.fromDisplayName(requirementRequest.getSpaceType()));
            requirement.setMaxAlumni(requirementRequest.getMaxAlumni());
            requirement.setSemester(requirementRequest.getSemester());
            requirementRepository.save(requirement);
            LOGGER.info("Created requirement: " + requirement);
        }
    }

}
