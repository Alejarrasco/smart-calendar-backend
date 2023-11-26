package bo.ucb.edu.smartcalendar.bl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bo.ucb.edu.smartcalendar.dto.OpenAIRequest;
import bo.ucb.edu.smartcalendar.dto.OpenAIResponse;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Space.SpaceType;
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

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public OpenAIBl(RestTemplate restTemplate, SpaceBl spaceBl, ScheduleBl scheduleBl, RequirementBl requirementBl) {
        this.restTemplate = restTemplate;
        this.spaceBl = spaceBl;
        this.scheduleBl = scheduleBl;
        this.requirementBl = requirementBl;
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

    public SmartcalResponse generatePlanificationsAutomatically(String spaceTypeString){

        LOGGER.info("Called Generate Prompt");

        SpaceType spaceType = SpaceType.fromDisplayName(spaceTypeString);
        if (spaceType == null) {
            throw new RuntimeException("Invalid space type");
        }

        //Prepare the data


        String context = "You are the assistant to the academic director of a university and you have to assign schedules to the subjects of the university. So that the spaces of the university are used in the best possible way, you have to assign the subjects to the spaces in such a way that the spaces are used as much as possible and the subjects are assigned to the spaces that best suit their requirements.\n" +
        "At the moment you are in charge of managing all the " + spaceType + "s of the university.";

        LOGGER.info("Context: \n" + context);

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
        "Where <space_name> is the name of the space you want to assign the subject to, <subject_code> is the code of the subject you want to assign, <day> is the day of the week you want to assign the subject to, <start_time> is the time of the day you want to start the class and <end_time> is the time of the day you want to end the class.\n";

        LOGGER.info("Prompt: \n" + prompt);

        /* //Create a request
        OpenAIRequest request = new OpenAIRequest(model, context, prompt);

        //Call the API
        OpenAIResponse response = restTemplate.postForObject(apiUrl, request, OpenAIResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("OpenAI API response is empty");
        } else if (validateResponse(response)) {
            throw new RuntimeException("OpenAI API response is invalid");
        }

        try {
            processResponseAssignations(response);
        } catch (Exception e) {
            throw new RuntimeException("Error processing OpenAI API response");
        } */

        SmartcalResponse smartcalResponse = new SmartcalResponse();
        smartcalResponse.setData("OK");
        return smartcalResponse;
    }
}
