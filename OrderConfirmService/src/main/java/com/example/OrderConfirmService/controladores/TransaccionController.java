package com.example.OrderConfirmService.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.OrderConfirmService.EstadoService;
import com.example.OrderConfirmService.modelos.RespuestaDTO;
import com.example.OrderConfirmService.modelos.TransaccionDTO;
import com.example.OrderConfirmService.servicios.TransaccionService;
import com.example.OrderConfirmService.utils.LogUtil;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;
    @Autowired
    private EstadoService estadoService;

    @PostMapping("/recibir")
    public ResponseEntity<RespuestaDTO> recibirTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
    	LogUtil.writeLog(TransaccionController.class.getSimpleName(), "Inicio del solicitud");
    	 // Leer el estado desde el archivo
        int estadoActual = estadoService.leerEstado();
        LogUtil.writeLog(TransaccionController.class.getSimpleName(), "Estado actual leído: " + estadoActual);

     
        RespuestaDTO respuestaDto= transaccionService.recibirTransaccion(transaccionDTO,estadoActual);
        
		// Devuelve estado de éxito
		return ResponseEntity.ok(new RespuestaDTO(respuestaDto.getEstado(), respuestaDto.getDescripcion()));
    }

    @GetMapping("/obtener")
    public TransaccionDTO obtenerTransaccion() {
        return null;// transaccionService.obtenerTransaccion();
    }
}
