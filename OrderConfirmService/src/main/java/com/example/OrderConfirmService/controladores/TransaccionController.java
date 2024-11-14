package com.example.OrderConfirmService.controladores;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.OrderConfirmService.modelos.RespuestaDTO;
import com.example.OrderConfirmService.modelos.TransaccionDTO;
import com.example.OrderConfirmService.servicios.TransaccionService;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/recibir")
    public ResponseEntity<RespuestaDTO> recibirTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        try {
            transaccionService.recibirTransaccion(transaccionDTO);
            // Devuelve estado de éxito
            return ResponseEntity.ok(new RespuestaDTO("01", "Transacción recibida y guardada"));
        } catch (IOException e) {
            e.printStackTrace();
            // Devuelve estado de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespuestaDTO("07", "Error al procesar la transacción"));
        }
    }

    @GetMapping("/obtener")
    public TransaccionDTO obtenerTransaccion() {
        return transaccionService.obtenerTransaccion();
    }
}
