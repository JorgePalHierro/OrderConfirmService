package com.example.OrderConfirmService.servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderConfirmService.ConfirmacionOrden;
import com.example.OrderConfirmService.ConfirmacionOrdenRepository;
import com.example.OrderConfirmService.controladores.TransaccionController;
import com.example.OrderConfirmService.modelos.RespuestaDTO;
import com.example.OrderConfirmService.modelos.TransaccionDTO;
import com.example.OrderConfirmService.utils.LogUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class TransaccionService {

	@Autowired
	private ApiService apiService;
	@Autowired
	private ConfirmacionOrdenRepository confirmacionOrdenRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final PatchService patchService;

	@Autowired
	public TransaccionService(PatchService patchService) {
		this.patchService = patchService;
	}

	public RespuestaDTO recibirTransaccion(TransaccionDTO transaccionDTO, int estadoArchivo) {
		String amount = "0";
		RespuestaDTO respuestaDto = new RespuestaDTO();
		
		LogUtil.writeLog(TransaccionController.class.getSimpleName(), "Datos orden:" + transaccionDTO.toString());

		// Se inicializa el campo consecutivo en 0 y si ya existe la Llave en BD se
		// aumenta el consecutivo
		transaccionDTO.setConsecutivo("0");
		int valorConsecutivo = Integer.parseInt(transaccionDTO.getConsecutivo());
		while (valorConsecutivo < 15) {
			if (existeRegistro(transaccionDTO, valorConsecutivo)) {
				valorConsecutivo++;

			} else {
				break;
			}
		}
		transaccionDTO.setConsecutivo(String.valueOf(valorConsecutivo));
		transaccionDTO.setConfirmacion("00");
		// Se lee el campo del archivo variable_estado.txt

		if (estadoArchivo == 1) {

			// Se raliza la llamada al servicio consulta
			String respuestaJson = apiService.getApiResponse(transaccionDTO.getNumeroOrden());

			String paymentStatus = "no_encontrado";

			if (!respuestaJson.contains("No se encontró la orden")) {

				try {
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode rootNode = objectMapper.readTree(respuestaJson);
					paymentStatus = rootNode.get("payment_status").asText();
					rootNode = objectMapper.readTree(respuestaJson);

					amount = rootNode.get("order_total").asText();   // Extrae el campo
																							// "c_originalPrice"
							

				} catch (Exception e) {
					e.printStackTrace();
				}
				amount = shiftDecimal(amount);
				
				
			}else {
				transaccionDTO.setConfirmacion("02");				
			}

		//	transaccionDTO.setConfirmacion("00");
			respuestaDto.setEstado("01");
			
			respuestaDto.setDescripcion(respuestaJson);

			if (!paymentStatus.contains("not_paid") && !paymentStatus.contains("no_encontrado")){

				transaccionDTO.setConfirmacion("01");
			}

			String instrumento = buscaInstrumento(respuestaJson); // Obtener el instrumento
			transaccionDTO.setInstrumento(instrumento);
			guardarConfirmacionOrden(transaccionDTO);

		}

		 guardarConfirmacionOrden(transaccionDTO);

		TransaccionDTO a = transaccionDTO;
		a.setConsecutivo("999");
		if (!amount.isEmpty()) {
			a.setImporte(amount);
		}
		
		System.out.println("Transaccion: " + transaccionDTO.toString());
		 guardarConfirmacionOrden(a);

		return respuestaDto;

	}

	@Transactional
	public ConfirmacionOrden guardarConfirmacionOrden(TransaccionDTO transaccionDTO) {
		new ConfirmacionOrden();
		LocalDateTime now = LocalDateTime.now();

		// Formatear la fecha y hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String formattedDateTime = now.format(formatter);
		transaccionDTO.setFechaCompleta(formattedDateTime);

		ConfirmacionOrden confirmacionOrden = ConfirmacionOrden.fromDTO(transaccionDTO);

		System.out.println("Registro guardado");

		return confirmacionOrdenRepository.save(confirmacionOrden);
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

	public boolean existeRegistro(TransaccionDTO transaccionDTO, int ValorC) {
		// Crear una consulta para verificar si ya existe un registro con las mismas
		// claves
		ConfirmacionOrden confirmacionOrden = confirmacionOrdenRepository
				.findByNumeroOrdenAndFechaAndTransaccionAndTerminalAndTiendaAndConsecutivo(
						transaccionDTO.getNumeroOrden(), transaccionDTO.getFecha(), transaccionDTO.getTransaccion(),
						transaccionDTO.getTerminal(), transaccionDTO.getTienda(), String.valueOf(ValorC));

		// Si el resultado es distinto de null, significa que ya existe un registro con
		// las claves
		return confirmacionOrden != null;
	}

	public static String shiftDecimal(String input) {
		try {
			// Convierte la cadena a un número flotante
			double number = Double.parseDouble(input);

			// Multiplica por 100 para mover el punto decimal dos lugares a la derecha
			long shiftedNumber = Math.round(number * 100);

			// Devuelve el número como cadena
			return String.valueOf(shiftedNumber);
		} catch (NumberFormatException e) {
			// Manejo de errores si la cadena no es válida
			System.err.println("Error: Entrada no válida");
			return null;
		}
	}
}
