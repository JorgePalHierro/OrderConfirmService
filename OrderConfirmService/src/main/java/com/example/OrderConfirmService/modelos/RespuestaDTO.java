package com.example.OrderConfirmService.modelos;

public class RespuestaDTO {
    public RespuestaDTO() {
		super();
	}

	private String estado;
    private String descripcion;

    // Constructor
    public RespuestaDTO(String estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
