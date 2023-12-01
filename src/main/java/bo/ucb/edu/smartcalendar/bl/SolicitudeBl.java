package bo.ucb.edu.smartcalendar.bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SolicitudeRequest;
import bo.ucb.edu.smartcalendar.entity.Assignation;
import bo.ucb.edu.smartcalendar.entity.Solicitude;
import bo.ucb.edu.smartcalendar.repository.AssignationRepository;
import bo.ucb.edu.smartcalendar.repository.PersonRepository;
import bo.ucb.edu.smartcalendar.repository.ScheduleRepository;
import bo.ucb.edu.smartcalendar.repository.SolicitudeRepository;
import bo.ucb.edu.smartcalendar.repository.SubjectRepository;

@Service
public class SolicitudeBl {
    
    Logger LOGGER = LoggerFactory.getLogger(SolicitudeBl.class);

    @Autowired
    private SolicitudeRepository solicitudeRepository;

    @Autowired
    private AssignationRepository assignationRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public SolicitudeBl(SolicitudeRepository solicitudeRepository, AssignationRepository assignationRepository, SubjectRepository subjectRepository, PersonRepository personRepository, ScheduleRepository scheduleRepository) {
        this.solicitudeRepository = solicitudeRepository;
        this.assignationRepository = assignationRepository;
        this.subjectRepository = subjectRepository;
        this.personRepository = personRepository;
    }

    public SmartcalResponse CreateSolicitude(SolicitudeRequest solicitudeRequest){
        LOGGER.info("Solicitude request: " + solicitudeRequest);

        Solicitude solicitude = new Solicitude();
        solicitude.setSubject(subjectRepository.findByCode(solicitudeRequest.getSubjectCode()));
        solicitude.setPerson(personRepository.findByPersonId(solicitudeRequest.getPersonId()));
        solicitude.setRegisterDate(new Date(System.currentTimeMillis()));
        solicitude.setSolicitudeStatus(Solicitude.SolicitudeStatus.PENDING);
        solicitude.setRecurrent(solicitudeRequest.isRecurrent());
        LOGGER.debug("Date range: " + Date.valueOf(solicitudeRequest.getStartDate()) + " - " + Date.valueOf(solicitudeRequest.getEndDate()));
        solicitude.setStartDate(Date.valueOf(solicitudeRequest.getStartDate()));
        if (solicitudeRequest.isRecurrent()){
            solicitude.setEndDate(Date.valueOf(solicitudeRequest.getEndDate()));
        } else {
            solicitude.setEndDate(null);
        }
        solicitudeRepository.save(solicitude);

        for (Integer periodId : solicitudeRequest.getPeriods()){
            try {
                LOGGER.info("Filing for period id: " + periodId);

                Assignation assignation = new Assignation();
                assignation.setSchedule(scheduleRepository.findByPeriodIdAndSpaceId(periodId, solicitudeRequest.getSpaceId()).get(0)); //TODO validate if space is open in schedule
                assignation.setsolicitude(solicitude);
                assignation.setApproveDate(null);
                assignation.setAiGenerated(false);
                assignationRepository.save(assignation);
            } catch (Exception e) {
                LOGGER.error("Error filing for period id: " + periodId + " under solicitude id: " + solicitude.getsolicitudeId());
                solicitudeRepository.delete(solicitude);
                throw new RuntimeException("Error filing for period id: " + periodId);
            }
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(solicitude);
        return response;
    }

    public SmartcalResponse GetSolicitudes(Integer personId){
        LOGGER.info("Getting solicitudes for person id: " + personId);

        if (personId == 0){
            return GetAllSolicitudes();
        } 

        SmartcalResponse response = new SmartcalResponse();
        try {
            response.setData(solicitudeRepository.findByPersonId(personId));
            if (response.getData() == null){
                throw new RuntimeException("No solicitudes found for person id: " + personId);
            }
        } catch (Exception e) {
            LOGGER.error("Error getting solicitudes for person id: " + personId);
        }
        return response;
    }

    public SmartcalResponse GetAllSolicitudes(){
        LOGGER.info("Getting all solicitudes");

        SmartcalResponse response = new SmartcalResponse();
        try {
            response.setData(solicitudeRepository.findAll());
            if (response.getData() == null){
                throw new RuntimeException("No solicitudes found");
            }
        } catch (Exception e) {
            LOGGER.error("Error getting all solicitudes");
        }
        return response;
    }

    public SmartcalResponse ApproveSolicitude(Integer token, Integer solicitudeId){
        LOGGER.info("Approving solicitude id: " + solicitudeId);

        Solicitude solicitude = solicitudeRepository.findBySolicitudeId(solicitudeId);
        if (solicitude == null){
            throw new RuntimeException("No solicitude found for id: " + solicitudeId);
        }
        List<Assignation> conflictingAssignations = solicitudeCausesConflicts(solicitude);
        if (conflictingAssignations.size() > 0){
            String conflictingAssString = "Solicitude causes conflicts with assignations:";
            for (Assignation conflictingAssignation : conflictingAssignations){
                conflictingAssString += conflictingAssignation.getsolicitude().getsolicitudeId() + " "+ conflictingAssignation.getsolicitude().getSubject().getSubjectName() + " " + conflictingAssignation.getsolicitude().getRegisterDate() + "\n";
            }
            throw new RuntimeException(conflictingAssString);
        }
        solicitude.setSolicitudeStatus(Solicitude.SolicitudeStatus.APPROVED);
        solicitudeRepository.save(solicitude);
        List<Assignation> assignations = assignationRepository.findBySolicitudeId(solicitudeId);
        for (Assignation assignation : assignations){
            assignation.setApproveDate(new Date(System.currentTimeMillis()));
            assignationRepository.save(assignation);
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(solicitude);
        return response;
    }

    public SmartcalResponse RejectSolicitude(Integer token, Integer solicitudeId){
        //TODO: Validate if token belongs to an administrator
        LOGGER.info("Rejecting solicitude id: " + solicitudeId);

        Solicitude solicitude = solicitudeRepository.findBySolicitudeId(solicitudeId);
        if (solicitude == null){
            throw new RuntimeException("No solicitude found for id: " + solicitudeId);
        }
        solicitude.setSolicitudeStatus(Solicitude.SolicitudeStatus.REJECTED);
        solicitudeRepository.save(solicitude);

        List<Assignation> assignations = assignationRepository.findBySolicitudeId(solicitudeId);
        for (Assignation assignation : assignations){
            assignation.setAssignationStatus(false);
            assignationRepository.save(assignation);
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(solicitude);
        return response;
    }

    public SmartcalResponse CancelSolicitude(Integer token, Integer solicitudeId){
        //TODO: Validate if token belongs to the solicitude responsible
        LOGGER.info("Cancelling solicitude id: " + solicitudeId);

        Solicitude solicitude = solicitudeRepository.findBySolicitudeId(solicitudeId);
        if (solicitude == null){
            throw new RuntimeException("No solicitude found for id: " + solicitudeId);
        }
        solicitude.setSolicitudeStatus(Solicitude.SolicitudeStatus.CANCELED);
        solicitudeRepository.save(solicitude);

        List<Assignation> assignations = assignationRepository.findBySolicitudeId(solicitudeId);
        for (Assignation assignation : assignations){
            assignation.setAssignationStatus(false);
            assignationRepository.save(assignation);
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(solicitude);
        return response;
    }

    private List<Assignation> solicitudeCausesConflicts(Solicitude solicitude){
        List<Assignation> conflictingAssignations = new ArrayList<Assignation>();
        List<Assignation> assignations = assignationRepository.findBySolicitudeId(solicitude.getsolicitudeId());
        for (Assignation assignation : assignations){
            List<Assignation> mayCauseConflictAssignations = assignationRepository.findByScheduleId(assignation.getSchedule().getScheduleId());
            for (Assignation conflictingAssignation : mayCauseConflictAssignations){
                if (conflictingAssignation.getAssignationId() != assignation.getAssignationId()){
                    if (conflictingAssignation.getsolicitude().getsolicitudeStatus() == Solicitude.SolicitudeStatus.APPROVED){
                        conflictingAssignations.add(conflictingAssignation);
                    }
                }
            }
        }
        return conflictingAssignations;
    }

}
