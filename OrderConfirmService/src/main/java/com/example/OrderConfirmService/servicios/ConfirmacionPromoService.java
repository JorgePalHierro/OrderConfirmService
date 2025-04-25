package com.example.OrderConfirmService.servicios;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderConfirmService.modelos.ConfirmacionPromo;
import com.example.OrderConfirmService.modelos.ConfirmacionPromoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmacionPromoService {

    @Autowired
    private ConfirmacionPromoRepository repository;

    public List<ConfirmacionPromo> findAll() {
        return repository.findAll();
    }

  
    public ConfirmacionPromo save(ConfirmacionPromo confirmacionPromo) {
        return repository.save(confirmacionPromo);
    }

   
}
