package com.example.OrderConfirmService.servicios;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderConfirmService.modelos.TransaccionDTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PatchService {

	private final OkHttpClient okHttpClient;

	

	@Autowired
	public PatchService(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}

	public String sendPatchRequest(TransaccionDTO transaccion) throws IOException {
		String url = "https://ph-bypass-pos-exp-api.us-w1.cloudhub.io/api/v1/orders/" + transaccion.getNumeroOrden()
				+ "/payment-instruments/" + transaccion.getInstrumento();
	
		
		String ticket = transaccion.getTienda() + transaccion.getTerminal() + transaccion.getTransaccion() +  transaccion.getFecha();
		ticket = numeroVerificador(ticket);

			
			
			transaccion.setTipo(tablaConversion(transaccion.getTienda()));

			System.out.println("*************---Importe:"+transaccion.getImporte() );
			// JSON request body
			String json = "{\n" + "  \"amount\": " + transaccion.getImporte()  + ",\n"
					+ "  \"payment_method_id\": \"PAY_IN_STORE\",\n" + "  \"c_paymentType\": \""
					+ transaccion.getTipo() + "\",\n" + "  \"payment_card\": {\n" + "    \"card_type\": \""
					+ transaccion.getTipo()  + "\",\n" + "    \"number\": \"" + transaccion.getNumeroTarjeta()
					+ "\",\n" + "    \"holder\": \"VENTA ASISTIDA\"\n" + "  },\n" + "  \"c_AS_palacioStoreID\": \""
					+ transaccion.getTienda() + "\",\n" + "  \"c_AS_employeeId\": \"" + transaccion.getVendedor() + "\",\n"
					+ "  \"c_AS_ticketNumber\": \"" + ticket + "\",\n"
					+ "  \"c_AS_monedasBarcode\": \"000000000000000000000\",\n" + "  \"c_AS_palacioPoints\": 0,\n"
					+ "  \"c_AS_monedas\": 0,\n" + "	  \"c_AS_detailedPaymentInformation\": \"[{\\\"schemaId\\\":\\\""
					+ transaccion.getEsquema()  + "\\\",\\\"transactionDateTime\\\":\\\"" + obtenerFechaHoraActual()
					+ "\\\",\\\"externalTransactionCode\\\":\\\"" + transaccion.getTransaccion()
					+ "\\\",\\\"transactionID\\\":\\\"4008306119\\\",\\\"originalTerminalId\\\":\\\"\\\",\\\"originalTransactionID\\\":\\\"\\\",\\\"transactionInfoCount\\\":\\\""
					+ transaccion.getTransaccion() + "\\\",\\\"terminal\\\":\\\"" + transaccion.getTerminal()
					+ "\\\",\\\"approval\\\":\\\"" + transaccion.getNumeroAutorizacion()
					+ "\\\",\\\"responseCode\\\":\\\"" + transaccion.getCodigoRespuesta()
					+ "\\\",\\\"responseDisplay\\\":\\\"TRANSACCION APROBADA\\\"}]\"\n" + "}";

			RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
			System.out.println("Json peticion:\n" + json );
			Request request = new Request.Builder().url(url).patch(body)
					.addHeader("client_id", "87cd348c017447b9991fc1eb4e0cccd4")
					.addHeader("client_secret", "de22aa5e10514445Ac9eDa16475E5bFf").build();

			try (Response response = okHttpClient.newCall(request).execute()) {
				if (response.isSuccessful()) {
					return response.body().string();
				} else {
					String errorResponse = response.body() != null ? response.body().string() : "No response body";
					throw new IOException("Failed : HTTP error code : " + response.code() + ", reason: "
							+ response.message() + ", details: " + errorResponse);
				}
			}

		
					
		
				
		
	}

	private String tablaConversion(String pos_TENDER_TYPE_CODE) {
		switch (pos_TENDER_TYPE_CODE) {
		case "1":
			return "Efectivo";
		case "8":
			return "TCPH";
		case "4":
		case "5":
			return "Bcos";
		case "3":
			return "AMEX";
		case "10":
			return "CUPH";
		case "23":
			return "Vales";
		case "2":
			return "Cheque";
		default:
			return "UNKNOWN_CODE";
		}
	}

	public static String numeroVerificador(String ticket) {

		int residuo;

		String todo = ticket;
		ArrayList numeros = new ArrayList();

		char[] valores = todo.toCharArray();

		for (int i = 0; i < valores.length; i = i + 2) {
			int a = (Character.getNumericValue(valores[i])) * 3;

			numeros.add(a);
		}

		for (int i = 1; i < valores.length; i = i + 2) {
			int b = Character.getNumericValue(valores[i]);

			numeros.add(b);
		}
		int suma = 0;
		for (int i = 0; i < numeros.size(); i++) {

			int num = (int) numeros.get(i);
			suma = suma + num;
		}

		residuo = suma % 10;
		if (residuo != 0) {
			residuo = 10 - residuo;
		}

		ticket = todo + residuo;
		return ticket;
	}

	public static String obtenerFechaHoraActual() {
		// Obtener la fecha y hora actual
		LocalDateTime fechaHoraActual = LocalDateTime.now();

		// Formatear la fecha y hora en el formato requerido
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return fechaHoraActual.format(formato);
	}

}
