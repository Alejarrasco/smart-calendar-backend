package bo.ucb.edu.smartcalendar.bl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.ucb.edu.smartcalendar.dto.Message;
import bo.ucb.edu.smartcalendar.dto.OpenAIRequest;
import bo.ucb.edu.smartcalendar.dto.OpenAIResponse;
import bo.ucb.edu.smartcalendar.dto.ParseAIResponse;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.OpenAIResponse.Choice;
import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
import bo.ucb.edu.smartcalendar.entity.Assignation;
import bo.ucb.edu.smartcalendar.entity.Planification;
import bo.ucb.edu.smartcalendar.entity.Requirement;
import bo.ucb.edu.smartcalendar.entity.Space;

@Service
public class OpenAIBl {

    Logger LOGGER = LoggerFactory.getLogger(OpenAIBl.class);
    
    @Qualifier("openAIRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpaceBl spaceBl;

    @Autowired
    private ScheduleBl scheduleBl;

    @Autowired
    private RequirementBl requirementBl;

    @Autowired
    private PlanificationViewBl planificationViewBl;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public OpenAIBl(RestTemplate restTemplate, SpaceBl spaceBl, ScheduleBl scheduleBl, RequirementBl requirementBl, PlanificationViewBl planificationViewBl) {
        this.restTemplate = restTemplate;
        this.spaceBl = spaceBl;
        this.scheduleBl = scheduleBl;
        this.requirementBl = requirementBl;
        this.planificationViewBl = planificationViewBl;
    }

    private String selectSpaces(SpaceType spaceType){
        LOGGER.info("Called Generate Prompt - selectSpaces");
        Integer maximumCapacityRequired = requirementBl.getMaximumCapacity(spaceType);
        LOGGER.info("Maximum capacity required: " + maximumCapacityRequired);
        LOGGER.info("Space type required: " + spaceType);
        List<Space> spaces = null;
        try {
            spaces = spaceBl.getSpacesByRequirements(spaceType, maximumCapacityRequired);
            LOGGER.info("Spaces found: " + spaces.size());
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
            e.printStackTrace();
        }
        String spacesString = "";
        for (Space space : spaces) {
            spacesString += space.getSpaceName() + ": From "+ scheduleBl.earliestOpenTime(space) + " to " + scheduleBl.latestCloseTime(space) + "\n";
        }
        LOGGER.info(spacesString);
        return spacesString;
    }

    private String selectSubjects(SpaceType spaceType) {
        LOGGER.info("Called Generate Prompt - selectSubjects");
        List<Requirement> requirements = requirementBl.getRequirementsBySpaceType(spaceType);
        String subjectsString = "";
        for (Requirement requirement : requirements) {
            subjectsString += requirement.getSubject().getSubjectCode() + ": " + requirementBl.totalTimeRequired(requirement) + " per class with " + requirement.getClassesPerWeek() + " classes per week\n";
        }
        LOGGER.info(subjectsString);
        return subjectsString;
    }

    private boolean validateResponse(OpenAIResponse response) {
        LOGGER.info("Called Generate Prompt - validateResponse");
        ObjectMapper mapper = new ObjectMapper();
        try {
            ParseAIResponse[] parseAIResponses = mapper.readValue(response.getChoices().get(0).getMessage().getContent(), ParseAIResponse[].class);
            for (ParseAIResponse parseAIResponse : parseAIResponses) {
                if (parseAIResponse.getSpace() == null || parseAIResponse.getSchedules() == null || parseAIResponse.getSchedules().isEmpty()) {
                    LOGGER.error("Error: Invalid response at space or schedules ");
                    return false;
                }
                for (ParseAIResponse.Schedule schedule : parseAIResponse.getSchedules()) {
                    if (schedule.getSubject_code() == null || schedule.getAssigned_hours() == null || schedule.getAssigned_hours().isEmpty()) {
                        LOGGER.error("Error: Invalid response at subject_code or assigned_hours ");
                        return false;
                    }
                    for (ParseAIResponse.Schedule.AssignedHour assignedHour : schedule.getAssigned_hours()) {
                        if (assignedHour.getDay() == null || assignedHour.getStart_time() == null || assignedHour.getEnd_time() == null) {
                            LOGGER.error("Error: Invalid response at day, start_time or end_time ");
                            return false;
                    }
                }
            }
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

   /*  private List<Assignation> processResponseAssignations(OpenAIResponse response) {
        LOGGER.info("Called Generate Prompt - processResponseAssignations");
        ObjectMapper mapper = new ObjectMapper();
        List<Assignation> assignations = new ArrayList<>();
        try {
            ParseAIResponse[] parseAIResponses = mapper.readValue(response.getChoices().get(0).getMessage().getContent(), ParseAIResponse[].class);
            for (ParseAIResponse parseAIResponse : parseAIResponses) {
                Space space = spaceBl.getSpaceByName(parseAIResponse.getSpace());
                for (ParseAIResponse.Schedule schedule : parseAIResponse.getSchedules()) {
                    Requirement requirement = requirementBl.getRequirementBySubjectCode(schedule.getSubject_code());
                    for (ParseAIResponse.Schedule.AssignedHour assignedHour : schedule.getAssigned_hours()) {
                        Assignation assignation = new Assignation();
                        assignation.setSpace(space);
                        assignation.setRequirement(requirement);
                        assignation.setDay(assignedHour.getDay());
                        assignation.setStartTime(assignedHour.getStart_time());
                        assignation.setEndTime(assignedHour.getEnd_time());
                        assignations.add(assignation);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
            e.printStackTrace();
            return null;
        }
        return assignations;
    }
 */
    private String writePlanifications(int spaceId){
        LOGGER.info("Called Generate Prompt - writePlanifications");
        List<Planification> plans = planificationViewBl.getPlanificationView(spaceId);
        String planif = "";
        for (Planification plan : plans) {
            planif += "Asignacion: " + plan.getAssignationId() + "\n" +
            "Dia: " + plan.getWeekDay() + "\n" +
            "Horario: " + plan.getStartTime() + " - " + plan.getEndTime() + "\n" +
            "Materia: " + plan.getSubjectName() + "\n" +
            "Profesor: " + plan.getFirstName() + " " + plan.getLastName() + "\n" +
            "\n";
        }

        return planif;
    }

    private String writePreferences(int spaceId) {
        LOGGER.info("Called Generate Prompt - writePreferences");
        List<Requirement> plans = requirementBl.getRequirementsBySpaceSinceLastMonday(spaceId);
        String prefs = "";
        LOGGER.info("Requirements found: " + plans.size());
        for (Requirement plan : plans) {
            LOGGER.info(plan.toString());
            prefs += "Materia: " + plan.getSubject().getSubjectName() + "\n";
            //"Profesor: " + plan.getSubject().getFirstName() + " " + plan.getSubject().getLastName() + "\n" +
            if (plan.getPreferences() == null) {
                prefs += "Preferencias: Sin preferencias\n";
            } else {
                prefs += "Preferencias: " + plan.getPreferences() + "\n";
            }
            prefs += "\n";
        }

        return prefs;

    }

    public SmartcalResponse AskAiReview(Integer spaceId) {
        LOGGER.info("Called AskAiReview");
        String context = "Eres el asistente del director academico de una universidad y tienes que moderar entre las preferencias de los profesores y la disponibilidad de los distintos espacios en la universidad, para ello dispones de la planificacion actual de las asignaciones y los comentarios de los profesores sobre sus preferencias de asignacion.\n";

        Space space = spaceBl.getSpaceById(spaceId);


        String prompt = "A continuacion se encuentra una lista de las asignaciones actuales del "+space.getSpaceType().getDisplayName()+" "+space.getSpaceName()+", con alguna información adicional sobre el mismo:\n" +

        writePlanifications(spaceId) +

        "A partir de los siguientes comentarios de docentes que han solicitado espacios para sus clases en el "+space.getSpaceType().getDisplayName()+" "+space.getSpaceName()+", indica que personas estarian descontentas con su asignacion actual y por que:\n"+

        writePreferences(spaceId);

        LOGGER.info("System: \n" + context);
        LOGGER.info("Prompt: \n" + prompt);

        //Create a request
        OpenAIRequest request = new OpenAIRequest(model, context, prompt);

        LOGGER.info("Request: \n" + request.toString());
        
        //Call the API
        OpenAIResponse response;
        try {
            response = restTemplate.postForObject(apiUrl, request, OpenAIResponse.class);

            LOGGER.info("Response: \n" + response.toString());

            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                throw new RuntimeException("OpenAI API response is empty");
            } 

            SmartcalResponse smartcalResponse = new SmartcalResponse();
            smartcalResponse.setData(response.getChoices().get(0).getMessage().getContent());
            return smartcalResponse;
        } catch (RestClientException e) {
            LOGGER.error("Error: " + e);
            e.printStackTrace();
            throw new RuntimeException("Error processing OpenAI API response"+e.getMessage());

        }

        

    }   

    public SmartcalResponse generatePlanificationsAutomatically(String spaceTypeString){

        LOGGER.info("Called Generate Prompt");

        SpaceType spaceType = SpaceType.fromDisplayName(spaceTypeString);
        if (spaceType == null) {
            throw new RuntimeException("Invalid space type");
        }

        //Prepare the data

        /*
        String context = "Eres el asistente del director académico de una universidad y tienes que moderar entre las preferencias de los profesores y la disponibilidad de los distintos espacios en la universidad\n"+
        "Esta vez estás a cargo de los "+spaceType.getDisplayName() +"s de la universidad.";

        LOGGER.info("Context: \n" + context);

        String prompt = "A continuación se encuentra una lista de los "+spaceType.getDisplayName() +"s de la universidad, con sus respectivas restricciones de horario y capacidad máxima de personas:\n" +
        selectAssignations +
        "A partir de los siguientes comentarios de docentes que han solicitado espacios para sus clases en los "+spaceType.getDisplayName() +"s de la universidad, indica qué personas estarían discontentas con su asignación actual y por qué:"+



        
        String prompt = "You have to assign schedules the following subjects to the following spaces, such that no subject is assigned to more than one space during the same day and all the subjects requirements match the spaces restrictions without overlapping one another:\n" +
        "Subjects:\n" +
        selectSubjects(spaceType) +
        "Spaces:\n" +
        selectSpaces(spaceType) +
        "Return your answer in a JSON following this format: \n" +
        "{\n" +
        "    \"space\": \"<space_name>\",\n" +
        "    \"schedule\": {\n" +
        "        \"subject\": {\n" +
        "            \"subject_code\": \"<subject_code>\",\n" +
        "            \"assigned_hours\": [\n" +
        "                {\n" +
        "                    \"day\": \"<day>\",\n" +
        "                    \"start_time\": \"<start_time>\",\n" +
        "                    \"end_time\": \"<end_time>\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }\n" +
        "    }\n" +
        "}\n" +
        "Where <space_name> is the name of the space you want to assign the subject to, <subject_code> is the code of the subject you want to assign, <day> is the day of the week you want to assign the subject to, <start_time> is the time of the day you want to start the class and <end_time> is the time of the day you want to end the class.\n";*/

        //LOGGER.info("Prompt: \n" + prompt);

        /* //Create a request
        OpenAIRequest request = new OpenAIRequest(model, context, prompt);

        //Call the API
        OpenAIResponse response = restTemplate.postForObject(apiUrl, request, OpenAIResponse.class);
        */

        //Just for testing read the file at \http\test.json and try to parse it

        Path filepath = Paths.get("src/main/java/bo/ucb/edu/smartcalendar/http/test.json");
        //String fileContent = Files.readString(filepath);

        OpenAIResponse response = new OpenAIResponse(new ArrayList<>());
        //response.getChoices().add(new Choice(0, new Message("response", fileContent)));

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("OpenAI API response is empty");
        } else if (validateResponse(response)) {
            throw new RuntimeException("OpenAI API response is invalid");
        }

        try {
            //processResponseAssignations(response);
        } catch (Exception e) {
            throw new RuntimeException("Error processing OpenAI API response");
        } 

        SmartcalResponse smartcalResponse = new SmartcalResponse();
        smartcalResponse.setData("OK");
        return smartcalResponse;
    }
}
