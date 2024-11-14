package com.example.OrderConfirmService.servicios;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderConfirmService.modelos.TransaccionDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransaccionService {

	
    private TransaccionDTO transaccion;
    @Autowired
	private ApiService apiService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private final PatchService patchService;

    @Autowired
    public TransaccionService(PatchService patchService) {
        this.patchService = patchService;
    }


    public void recibirTransaccion(TransaccionDTO transaccionDTO)  {
        this.transaccion = transaccionDTO;
        String resp=null;
        // Aquí puedes agregar la lógica para procesar los datos o guardarlos
        String respuestaJson = apiService.getApiResponse(transaccion.getNumeroOrden());
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(respuestaJson);

            // Extrae el valor de statusMessage
            String statusMessage = rootNode.path("details")
                                           .path("fault")
                                           .path("arguments")
                                           .path("statusMessage")
                                           .asText();

            // Almacena el valor de statusMessage en una variable String
            System.out.println("Status Message: " + statusMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (!respuestaJson.contains("No se encontró la orden")) {
			String instrumento = buscaInstrumento(respuestaJson); // Obtener el instrumento
			transaccion.setInstrumento(instrumento);
			
			System.out.println("Instrumento consultado: " + instrumento); // Depuración			
			
			try {
				resp = patchService.sendPatchRequest(transaccion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}
			
			System.out.println(resp);
			
		} else {
		
		}
        
     
    }

    public TransaccionDTO obtenerTransaccion() {
        return this.transaccion;
    }
    
    public String buscaInstrumento(String consultaJson) {
		try {
            // Parse the JSON string
            JsonNode rootNode = objectMapper.readTree(consultaJson);
            
            // Navigate to the payment_instruments array
            JsonNode paymentInstruments = rootNode.path("payment_instruments");
            
            // Check if the array has elements
            if (paymentInstruments.isArray() && paymentInstruments.size() > 0) {
                // Get the first object in the array
                JsonNode firstPaymentInstrument = paymentInstruments.get(0);
                
                // Get the payment_instrument_id
                return firstPaymentInstrument.path("payment_instrument_id").asText();
            } else {
                return "No payment instruments found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing JSON";
        }
    }
}
