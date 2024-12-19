package com.example.OrderConfirmService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EstadoService {

    @Value("${estado.file.path}")
    private String filePath;

    // Leer el estado desde el archivo
    public int leerEstado() {
        try {
            String content = Files.readString(Paths.get(filePath)).trim();
            return Integer.parseInt(content); // Convierte el contenido a entero
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error al leer el estado desde el archivo: " + e.getMessage());
        }
    }

    // Actualizar el estado en el archivo
    public void actualizarEstado(int nuevoEstado) {
        if (nuevoEstado != 0 && nuevoEstado != 1) {
            throw new IllegalArgumentException("El estado debe ser 0 o 1.");
        }

        try {
            Files.writeString(Paths.get(filePath), String.valueOf(nuevoEstado));
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el estado en el archivo: " + e.getMessage());
        }
    }
}
