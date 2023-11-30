package bo.ucb.edu.smartcalendar.bl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.RequirementRequest;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Requirement;
import bo.ucb.edu.smartcalendar.entity.Subject;
import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
import bo.ucb.edu.smartcalendar.repository.RequirementRepository;
import bo.ucb.edu.smartcalendar.repository.SubjectRepository;

@Service
public class RequirementBl {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RequirementBl.class);
    
    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public RequirementBl(RequirementRepository requirementRepository, SubjectRepository subjectRepository) {
        this.requirementRepository = requirementRepository;
        this.subjectRepository = subjectRepository;
    }

    //TODO
    // public SmartcalResponse ListRequirements(){}

    public void CreateRequirements(RequirementRequest[] requirements, Subject subject){
        //TODO manage hidden exceptions
        LOGGER.info("Called CreateRequirements");
        for(RequirementRequest requirementRequest : requirements){
            Requirement requirement = new Requirement();
            //LOGGER.info("Received requirement: " + requirementRequest);
            LOGGER.info(subject.toString());
            requirement.setSubject(subject);
            requirement.setPeriodsPerClass(requirementRequest.getPeriodsPerClass());
            requirement.setClassesPerWeek(requirementRequest.getClassesPerWeek());
            requirement.setSpaceType(SpaceType.fromDisplayName(requirementRequest.getSpaceType()));
            requirement.setMaxAlumni(requirementRequest.getMaxAlumni());
            requirement.setSemester(requirementRequest.getSemester());
            requirement.setPreferences(requirementRequest.getPreferences());
            requirementRepository.save(requirement);
            LOGGER.info("Created requirement: " + requirement);
        }
    }

    public SmartcalResponse AddRequirement(RequirementRequest requirementRequest){
        LOGGER.info("Called AddRequirement");
        Requirement requirement = new Requirement();
        requirement.setSubject(subjectRepository.findByCode(requirementRequest.getSubjectCode()));
        requirement.setPeriodsPerClass(requirementRequest.getPeriodsPerClass());
        requirement.setClassesPerWeek(requirementRequest.getClassesPerWeek());
        requirement.setSpaceType(SpaceType.fromDisplayName(requirementRequest.getSpaceType()));
        requirement.setMaxAlumni(requirementRequest.getMaxAlumni());
        requirement.setSemester(requirementRequest.getSemester());
        requirement.setPreferences(requirementRequest.getPreferences());
        requirementRepository.save(requirement);
        LOGGER.info("Created requirement: " + requirement);
        SmartcalResponse response = new SmartcalResponse();
        response.setData(requirement);
        return response;
    }

    public Integer getMaximumCapacity(SpaceType spaceType){
        LOGGER.info("Called getMaximumCapacity");
        Integer maximumCapacity = 0;
        try {
            maximumCapacity = requirementRepository.findMaximumCapacityBySpaceType(spaceType.name());
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }
        return maximumCapacity;
    }
    
    public List<Requirement> getRequirementsBySpaceType(SpaceType spaceType){
        LOGGER.info("Called getRequirements");
        List<Requirement> requirements = requirementRepository.findBySpaceType(spaceType.name());
        return requirements;
    }

    public String totalTimeRequired(Requirement requirement) {
        LOGGER.info("Called totalTimeRequired");
        Integer totalTimeRequired = requirement.getPeriodsPerClass();
        if (totalTimeRequired == 2){
            return totalTimeRequired + " hour";
        } else if (totalTimeRequired == 3){
            return totalTimeRequired + " hour and 30 minutes";
        } else if (totalTimeRequired % 2 == 0) {
            return totalTimeRequired / 2 + " hours";
        } else {
            return totalTimeRequired / 2 + " hours and 30 minutes";
        }
    }
    
}
