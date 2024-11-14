package com.example.OrderConfirmService.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.OrderConfirmService.AuthService;
import com.example.OrderConfirmService.LoginForm;
import com.example.OrderConfirmService.modelos.TransaccionDTO;
import com.example.OrderConfirmService.servicios.TiendasRepository;



@Controller

public class OrderConfirmController {
	String user_number;
	@Autowired
	private AuthService authService;
	@Autowired
	private TiendasRepository tiendasRepository;
	
	@GetMapping("/")
	public String redireccionar(Model model) {
		System.out.println("Logeo");
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/ordenesconfirmadas")
	public String login(@ModelAttribute LoginForm loginForm, Model model) {


		 List<String> tiendas = Arrays.asList("Tienda 1", "Tienda 2", "Tienda 3");
		// List<String> tiendas = cargarListaTiendas();
		   
	        
	    model.addAttribute("tiendas", tiendas);
		user_number = loginForm.getUsername();
		 if (authService.validate(loginForm.getUsername(), loginForm.getPassword())) {
		
	
		return "index";
		
		  } else { model.addAttribute("error", "Usuario o contraseña incorrectos");
		  return "login"; }
		 
	}
	
	
	 
	 // Método para filtrar las transacciones
	    @PostMapping("/filtrar")
	    public String filtrarDatos(@RequestParam String tienda, @RequestParam String fecha, Model model) {
	        // Aquí llamas a tu método que devuelve las transacciones filtradas
	        List<TransaccionDTO> transacciones = obtenerTransaccionesFiltradas("Tienda 1", "2024-11-01");

	        // Pasar las transacciones filtradas al modelo
	        model.addAttribute("transacciones", transacciones);

	        // Volver a enviar la lista de tiendas al modelo para el formulario de filtro
	        model.addAttribute("tiendas", Arrays.asList("Tienda 1", "Tienda 2", "Tienda 3"));
	        
	        return "index";  // Recargar la vista con los resultados
	    }
	    
	    // Método para obtener las transacciones filtradas
	    private List<TransaccionDTO> obtenerTransaccionesFiltradas(String tienda, String fecha) {
	        // Simulando las transacciones en la base de datos
	        List<TransaccionDTO> transacciones = Arrays.asList(
	            new TransaccionDTO("Tienda 1", "001", "12345", "2024-11-01", "A001", "Compra", "1234567890123456", "100", "Juan", "Esquema 1", "1234", "Aprobada", "Tarjeta"),
	            new TransaccionDTO("Tienda 2", "002", "12346", "2024-11-02", "A002", "Compra", "1234567890123457", "200", "Ana", "Esquema 2", "1235", "Aprobada", "Tarjeta"),
	            new TransaccionDTO("Tienda 3", "003", "12347", "2024-11-03", "A003", "Compra", "1234567890123458", "150", "Pedro", "Esquema 3", "1236", "Aprobada", "Efectivo"),
	            new TransaccionDTO("Tienda 1", "004", "12348", "2024-11-04", "A004", "Compra", "1234567890123459", "250", "Maria", "Esquema 4", "1237", "Pendiente", "Tarjeta")
	        );

	        // Filtrar por tienda y fecha
	        return transacciones.stream()
	                .filter(t -> (tienda.isEmpty() || t.getTienda().equals(tienda)) && 
	                             (fecha.isEmpty() || t.getFecha().equals(fecha)))
	                .collect(Collectors.toList());
	    }

	private List<String> cargarListaTiendas() {
		List<String> descripciones = tiendasRepository.findAll().stream()
				.map(tienda -> tienda.getCNUMTIEN() + " - " + tienda.getDDESCRIP()).sorted()
				.collect(Collectors.toList());
		return descripciones;
	}
	
}
