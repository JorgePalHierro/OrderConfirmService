package com.example.OrderConfirmService.modelos;

import java.util.List;

public class PromoRequest {
	private String codigo;
	private String tienda;
	private String terminal;
	private String transaccion;
	private String fecha;
	private String numeroOrden;
	private List<Articulo> articulos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "PromoRequest [codigo=" + codigo + ", tienda=" + tienda + ", terminal=" + terminal + ", transaccion="
				+ transaccion + ", fecha=" + fecha + ", numeroOrden=" + numeroOrden + "]";
	}

	public static class Articulo {
		private Long consecutivo;
		private String sku;
		private String descripcion;
		private String importe;
		private Integer cantidad;
		private Integer discount_automatic_1;
		private Integer discount_automatic_2;
		private Integer discount_automatic_3;
		private Integer discount_manual;
		private Integer discount_amount;
		private Integer discount_employee;
		private Integer sale_price;

		public Long getConsecutivo() {
			return consecutivo;
		}

		public void setConsecutivo(Long consecutivo) {
			this.consecutivo = consecutivo;
		}
		
		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public String getImporte() {
			return importe;
		}

		public void setImporte(String importe) {
			this.importe = importe;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		public Integer getDiscount_automatic_1() {
			return discount_automatic_1;
		}

		public void setDiscount_automatic_1(Integer discount_automatic_1) {
			this.discount_automatic_1 = discount_automatic_1;
		}

		public Integer getDiscount_automatic_2() {
			return discount_automatic_2;
		}

		public void setDiscount_automatic_2(Integer discount_automatic_2) {
			this.discount_automatic_2 = discount_automatic_2;
		}

		public Integer getDiscount_automatic_3() {
			return discount_automatic_3;
		}

		public void setDiscount_automatic_3(Integer discount_automatic_3) {
			this.discount_automatic_3 = discount_automatic_3;
		}

		public Integer getDiscount_manual() {
			return discount_manual;
		}

		public void setDiscount_manual(Integer discount_manual) {
			this.discount_manual = discount_manual;
		}

		public Integer getDiscount_amount() {
			return discount_amount;
		}

		public void setDiscount_amount(Integer discount_amount) {
			this.discount_amount = discount_amount;
		}

		public Integer getDiscount_employee() {
			return discount_employee;
		}

		public void setDiscount_employee(Integer discount_employee) {
			this.discount_employee = discount_employee;
		}

		public Integer getSale_price() {
			return sale_price;
		}

		public void setSale_price(Integer sale_price) {
			this.sale_price = sale_price;
		}

		@Override
		public String toString() {
			return "Articulo [consecutivo=" + consecutivo + ", sku=" + sku + ", importe=" + importe + ", cantidad="
					+ cantidad + ", discount_automatic_1=" + discount_automatic_1 + ", discount_automatic_2="
					+ discount_automatic_2 + ", discount_automatic_3=" + discount_automatic_3 + ", discount_manual="
					+ discount_manual + ", discount_amount=" + discount_amount + ", discount_employee="
					+ discount_employee + ", sale_price=" + sale_price + "]";
		}
	}

}
