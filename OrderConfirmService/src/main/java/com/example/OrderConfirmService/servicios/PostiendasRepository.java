package com.example.OrderConfirmService.servicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;





public interface PostiendasRepository extends JpaRepository<Postiendas, String> {
    // Aquí puedes añadir métodos personalizados si es necesario
}

