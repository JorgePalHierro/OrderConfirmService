package com.example.OrderConfirmService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Posusuarios, String> {
    // Aquí puedes añadir métodos personalizados si es necesario
}

