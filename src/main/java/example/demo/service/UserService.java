package example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.model.User;
import example.demo.repository.UserRepository;
import example.demo.utils.Utils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // object of utils class
    Utils utils = new Utils();

    public User saveUser(User user) {

        // hash the password
        String hashedPassword = utils.hashPassword(user.getPassword());

        user.setPassword(hashedPassword);

        System.out.println(user);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
}
