package example.demo.authorisation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final Authorisation authorisation;

    public AuthController(Authorisation authorisation ){
        this.authorisation=authorisation;
    }
 @PostMapping("/login")
    public Map<String, String> login(@RequestParam String email, @RequestParam String password) {
            String token = authorisation.generateToken(email);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        }

}
