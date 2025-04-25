package com.example.OrderConfirmService.modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "CONFIRMACION_PROMO")
@IdClass(ConfirmacionPromoId.class)
@Data
public class ConfirmacionPromo {

    @Id
    @Column(name = "CONSECUTIVO")
    private String consecutivo;

    @Id
    @Column(name = "TIENDA")
    private String tienda;

    @Id
    @Column(name = "TERMINAL")
    private String terminal;

    @Id
    @Column(name = "TRANSACCION")
    private String transaccion;

    @Id
    @Column(name = "FECHA")
    private String fecha;

    @Column(name = "FECHACOMPLETA")
    private LocalDateTime fechaCompleta;

    @Column(name = "NUMEROORDEN")
    private String numeroOrden;

    @Column(name = "SKU")
    private String sku;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "AUTOMATIC_DISCOUNT_1")
    private String automaticDiscount1;

    @Column(name = "AUTOMATIC_DISCOUNT_2")
    private String automaticDiscount2;

    @Column(name = "AUTOMATIC_DISCOUNT_3")
    private String automaticDiscount3;

    @Column(name = "MANUAL_DISCOUNT")
    private String manualDiscount;

    @Column(name = "AMOUNT_DISCOUNT")
    private String amountDiscount;

    @Column(name = "EMP_DISCOUNT")
    private String empDiscount;

    @Column(name = "SALE_PRICE")
    private String salePrice;


	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getFechaCompleta() {
		return fechaCompleta;
	}

	public void setFechaCompleta(LocalDateTime now) {
		this.fechaCompleta = now;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getAutomaticDiscount1() {
		return automaticDiscount1;
	}

	public void setAutomaticDiscount1(String automaticDiscount1) {
		this.automaticDiscount1 = automaticDiscount1;
	}

	public String getAutomaticDiscount2() {
		return automaticDiscount2;
	}

	public void setAutomaticDiscount2(String automaticDiscount2) {
		this.automaticDiscount2 = automaticDiscount2;
	}

	public String getAutomaticDiscount3() {
		return automaticDiscount3;
	}

	public void setAutomaticDiscount3(String automaticDiscount3) {
		this.automaticDiscount3 = automaticDiscount3;
	}

	public String getManualDiscount() {
		return manualDiscount;
	}

	public void setManualDiscount(String manualDiscount) {
		this.manualDiscount = manualDiscount;
	}

	public String getAmountDiscount() {
		return amountDiscount;
	}

	public void setAmountDiscount(String amountDiscount) {
		this.amountDiscount = amountDiscount;
	}

	public String getEmpDiscount() {
		return empDiscount;
	}

	public void setEmpDiscount(String empDiscount) {
		this.empDiscount = empDiscount;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public String toString() {
		return "ConfirmacionPromo [consecutivo=" + consecutivo + ", tienda=" + tienda + ", terminal=" + terminal
				+ ", transaccion=" + transaccion + ", fecha=" + fecha + ", fechaCompleta=" + fechaCompleta
				+ ", numeroOrden=" + numeroOrden + ", sku=" + sku + ", cantidad=" + cantidad + ", automaticDiscount1="
				+ automaticDiscount1 + ", automaticDiscount2=" + automaticDiscount2 + ", automaticDiscount3="
				+ automaticDiscount3 + ", manualDiscount=" + manualDiscount + ", amountDiscount=" + amountDiscount
				+ ", empDiscount=" + empDiscount + ", salePrice=" + salePrice + "]";
	}
}
