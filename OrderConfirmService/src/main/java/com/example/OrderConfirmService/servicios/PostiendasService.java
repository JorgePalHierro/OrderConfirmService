package com.example.OrderConfirmService.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostiendasService {
    @Autowired
    private PostiendasRepository Postiendas;

    public List<Postiendas> getAllUsers() {
        return Postiendas.findAll();
    }

    public Postiendas getUserByUsername(String username) {
        return Postiendas.findById(username).orElse(null);
    }

    public Postiendas createUser(Postiendas user) {
        return Postiendas.save(user);
    }

    public Postiendas updateUser(String username, Postiendas user) {
    	Postiendas existingUser = Postiendas.findById(username).orElse(null);
        if (existingUser != null) {
            
            return Postiendas.save(existingUser);
        }
        return null;
    }

    public void deleteUser(String username) {
        Postiendas.deleteById(username);
    }
}

