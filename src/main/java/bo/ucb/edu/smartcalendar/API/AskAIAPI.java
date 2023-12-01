package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.OpenAIBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(value = "/api/v1/ask_ai")
public class AskAIAPI {
    
    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AskAIAPI.class);

    @Autowired
    private OpenAIBl openAIBl;

    public AskAIAPI(OpenAIBl openAIBl) {
        this.openAIBl = openAIBl;
    }

    @PostMapping(value = "/{spaceId}")
    public SmartcalResponse AskAiReview(@PathVariable Integer spaceId, @RequestParam boolean call_api){
        LOGGER.info("Called AskAiReview "+ spaceId);
        SmartcalResponse smartcalResponse = new SmartcalResponse();
        try {
            // TODO : Call AI if call_api is true otherwise jsut print the prompt
            smartcalResponse = openAIBl.AskAiReview(spaceId);
            smartcalResponse.setCode("AUTO-3001");
            return smartcalResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            smartcalResponse.setCode("AUTO-6001");
            smartcalResponse.setErrormessage(e.getMessage());
            return smartcalResponse;
        }
    }
}
