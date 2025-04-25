package com.example.OrderConfirmService.modelos;

import lombok.Data;
import java.io.Serializable;

@Data
public class ConfirmacionPromoId implements Serializable {

    private String consecutivo;
    private String tienda;
    private String terminal;
    private String transaccion;
    private String fecha;
}