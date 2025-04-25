package com.example.OrderConfirmService.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("_ddMMyy");
    
    // Método para obtener el nombre del archivo con la fecha
    private static String getLogFileName() {
        String dateSuffix = LocalDateTime.now().format(FILE_DATE_FORMAT);
        return "program_log" + dateSuffix + ".txt";
    }

    // Método para escribir en el log
    public static void writeLog(String className, String message) {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String logMessage = String.format("[%s] [%s]: %s", timestamp, className, message);
        String logFileName = getLogFileName();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
