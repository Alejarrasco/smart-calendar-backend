package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.PlanificationViewBl;
import bo.ucb.edu.smartcalendar.dto.TestiDto;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {

    Logger LOGGER = LoggerFactory.getLogger(PlanificationViewBl.class);

    @PostMapping (path = "/protected")
    public TestiDto login(@RequestHeader("Authorization") String token, @RequestHeader("User-Agent") String userAgent, @RequestBody TestiDto body){
        LOGGER.info("Called login");
        System.out.println("Hello World "+token.split(" ")[1]);
        System.out.println("you are "+body.getName());
        return new TestiDto(userAgent);
    }
}
