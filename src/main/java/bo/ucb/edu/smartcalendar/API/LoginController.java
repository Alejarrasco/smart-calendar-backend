package bo.ucb.edu.smartcalendar.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.dto.TestiDto;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {

    @PostMapping (path = "/protected")
    public TestiDto login(@RequestHeader("Authorization") String token, @RequestHeader("User-Agent") String userAgent, @RequestBody TestiDto body){

        System.out.println("Hello World "+token);
        System.out.println("you are "+body.getName());
        return new TestiDto(userAgent);
    }
}
