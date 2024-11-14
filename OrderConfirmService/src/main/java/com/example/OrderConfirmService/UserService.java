package com.example.OrderConfirmService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Posusuarios> getAllUsers() {
        return userRepository.findAll();
    }

    public Posusuarios getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public Posusuarios createUser(Posusuarios user) {
        return userRepository.save(user);
    }

    public Posusuarios updateUser(String username, Posusuarios user) {
    	Posusuarios existingUser = userRepository.findById(username).orElse(null);
        if (existingUser != null) {
            
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    
}

