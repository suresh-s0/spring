package example.demo.authorisation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.demo.model.User;
import example.demo.repository.UserRepository;
import example.demo.utils.Utils;

@RestController    
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private Authorisation authorisation;
    private Utils utils;
// dependencie Injection
    public AuthController(UserRepository userRepository, Authorisation authorisation, Utils utils) {
    this.userRepository = userRepository;
    this.authorisation = authorisation;
    this.utils = utils;
    }
    
 @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
   
            // if (loginRequest.getPassword() == null) {
            //     return (Map<String, String>) ResponseEntity.badRequest().body("Password cannot be null");
            // }

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //Check if the user exists with correct email password
        User user = userRepository.findByEmail(email);

        if (user == null || !utils.comparePassword(user.getPassword(), password)) {
            throw new RuntimeException("Invalid email or password");
        }


        // called  generated Token method here
            String token = authorisation.generateToken(loginRequest.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        }

}
