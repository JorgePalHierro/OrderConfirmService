package com.example.OrderConfirmService.controladores;

//ConfirmacionPromoController.java



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.OrderConfirmService.modelos.ConfirmacionPromo;
import com.example.OrderConfirmService.modelos.PromoRequest;
import com.example.OrderConfirmService.servicios.ConfirmacionPromoService;
import com.example.OrderConfirmService.utils.LogUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/confirmacion-promo")
public class ConfirmacionPromoController {

 @Autowired
 private ConfirmacionPromoService service;

 
 private static final String CLASS_NAME = "ConfirmacionPromoController";
 
 @GetMapping
 public List<ConfirmacionPromo> getAll() {
     return service.findAll();
 }

 

 @PostMapping
 public List<ConfirmacionPromo> create(@RequestBody PromoRequest promoRequest) {
     List<ConfirmacionPromo> savedRecords = new ArrayList<>();
     LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
     LogUtil.writeLog(CLASS_NAME, "Solicitud para crear nuevas promociones. Petición: " + promoRequest);

     // Validación de campo 'codigo' (siempre debe ser '02')
     if (!"02".equals(promoRequest.getCodigo())) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'codigo' debe ser '02'.");
         throw new IllegalArgumentException("El campo 'codigo' debe ser '02'.");
     }

     // Validaciones generales de promoRequest
     if (promoRequest.getTienda() == null || promoRequest.getTienda().isEmpty()) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'tienda' es obligatorio.");
         throw new IllegalArgumentException("El campo 'tienda' es obligatorio.");
     }
     if (!promoRequest.getTienda().matches("\\d{4}")) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'tienda' debe contener exactamente 4 dígitos.");
         throw new IllegalArgumentException("El campo 'tienda' debe contener exactamente 4 dígitos.");
     }

     if (promoRequest.getTerminal() == null || promoRequest.getTerminal().isEmpty()) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'terminal' es obligatorio.");
         throw new IllegalArgumentException("El campo 'terminal' es obligatorio.");
     }
     if (!promoRequest.getTerminal().matches("\\d{3}")) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'terminal' debe contener exactamente 3 dígitos.");
         throw new IllegalArgumentException("El campo 'terminal' debe contener exactamente 3 dígitos.");
     }

     if (promoRequest.getTransaccion() == null || promoRequest.getTransaccion().isEmpty()) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'transacción' es obligatorio.");
         throw new IllegalArgumentException("El campo 'transacción' es obligatorio.");
     }
     if (!promoRequest.getTransaccion().matches("\\d{4}")) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'transacción' debe contener exactamente 4 dígitos.");
         throw new IllegalArgumentException("El campo 'transacción' debe contener exactamente 4 dígitos.");
     }

     if (promoRequest.getFecha() == null) {
         LogUtil.writeLog(CLASS_NAME, "El campo 'fecha' es obligatorio.");
         throw new IllegalArgumentException("El campo 'fecha' es obligatorio.");
     }

     System.out.println("Petición: " + promoRequest.toString() + promoRequest.getArticulos().toString());

     for (PromoRequest.Articulo articulo : promoRequest.getArticulos()) {
         // Validación de artículo
         if (articulo.getConsecutivo() == null) {
             throw new IllegalArgumentException("El campo 'consecutivo' es obligatorio.");
         }

         ConfirmacionPromo promo = new ConfirmacionPromo();
         promo.setTienda(promoRequest.getTienda());
         promo.setTerminal(promoRequest.getTerminal());
         promo.setTransaccion(promoRequest.getTransaccion());
         promo.setFecha(promoRequest.getFecha());
         promo.setFechaCompleta(now);
         promo.setNumeroOrden(promoRequest.getNumeroOrden());
         promo.setSku(articulo.getSku());
         promo.setDescripcion(articulo.getDescripcion());
         promo.setCantidad(articulo.getCantidad());
         promo.setConsecutivo(articulo.getConsecutivo().toString());
         promo.setAutomaticDiscount1(articulo.getDiscount_automatic_1().toString());
         promo.setAutomaticDiscount2(articulo.getDiscount_automatic_2().toString());
         promo.setAutomaticDiscount3(articulo.getDiscount_automatic_3().toString());
         promo.setManualDiscount(articulo.getDiscount_manual().toString());
         promo.setAmountDiscount(articulo.getDiscount_amount().toString());
         promo.setEmpDiscount(articulo.getDiscount_employee().toString());
         promo.setSalePrice(articulo.getSale_price().toString());

         ConfirmacionPromo savedPromo = service.save(promo);
         savedRecords.add(savedPromo);
         LogUtil.writeLog(CLASS_NAME, "Promoción creada: " + savedPromo);
     }
     LogUtil.writeLog(CLASS_NAME, "Se crearon " + savedRecords.size() + " promociones.");

     return savedRecords;
 }





}
