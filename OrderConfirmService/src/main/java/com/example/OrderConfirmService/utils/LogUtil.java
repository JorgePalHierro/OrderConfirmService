package com.example.OrderConfirmService.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

	private static final String LOG_FILE = "program_log.txt"; // Ruta al archivo de log
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// Método para escribir en el log
    public static void writeLog(String className, String message) {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String logMessage = String.format("[%s] [%s]: %s", timestamp, className, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
