package com.example.OrderConfirmService.modelos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmacionPromoRepository extends JpaRepository<ConfirmacionPromo, ConfirmacionPromoId> {
}
