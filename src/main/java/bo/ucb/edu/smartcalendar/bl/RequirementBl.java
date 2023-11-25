package bo.ucb.edu.smartcalendar.bl;

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
        requirementRepository.save(requirement);
        LOGGER.info("Created requirement: " + requirement);
        SmartcalResponse response = new SmartcalResponse();
        response.setData(requirement);
        return response;
    }

}
