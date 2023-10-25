package bo.ucb.edu.smartcalendar.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {
    @GetMapping (path = "/protected")
    public void login(@RequestHeader("Authorization") String token){

        System.out.println("Hello World "+token);
    }
}
