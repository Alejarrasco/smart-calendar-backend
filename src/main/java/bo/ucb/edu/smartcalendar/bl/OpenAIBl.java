package bo.ucb.edu.smartcalendar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import bo.ucb.edu.smartcalendar.dto.OpenAIRequest;
import bo.ucb.edu.smartcalendar.dto.OpenAIResponse;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

public class OpenAIBl {
    
    @Qualifier("openAIRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public SmartcalResponse generatePlanificationsAutomatically(){

        //Prepare the data

        String context = ""; //TODO: Format the context from Assignations

        String prompt = ""; //TODO: Format the prompt from Assignations

        //Create a request
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
        }

        SmartcalResponse smartcalResponse = new SmartcalResponse();
        smartcalResponse.setData("OK");
        return smartcalResponse;
    }
}
