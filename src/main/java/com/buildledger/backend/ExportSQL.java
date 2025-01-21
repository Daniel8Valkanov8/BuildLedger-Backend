package com.buildledger.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ExportSQL {

    @Value("${spring.datasource.username}")
    private String dbUser; // Извличаме от application.yaml

    @Value("${spring.datasource.password}")
    private String dbPassword; // Извличаме от application.yaml

    @Value("${spring.datasource.url}")
    private String dbUrl; // Извличаме от application.yaml

    @Value("${spring.mysqldump.path}")
    private String mysqldumpPath; // Пътят към mysqldump.exe от application.yaml

    private static final String DB_NAME = "buildLedgerSchema";
    private static final String OUTPUT_PATH = "src/main/resources/sql/dump.sql";

    @Scheduled(fixedRate = 60000) // Изпълнява се на всяка минута (60,000 милисекунди)
    public void dumpDatabase() {
        synchronized (this) {
            try {
                File dumpFile = new File(OUTPUT_PATH);
                if (dumpFile.exists()) {
                    dumpFile.delete();
                }

                ProcessBuilder pb = new ProcessBuilder(
                        mysqldumpPath, // Използваме стойността от application.yaml
                        "-u" + dbUser, // Използваме стойността от application.yaml
                        "-p" + dbPassword, // Използваме стойността от application.yaml
                        "--add-drop-database",
                        "--databases", DB_NAME,
                        "--result-file=" + dumpFile.getAbsolutePath()
                );

                Process process = pb.start();
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("Database dump created successfully at: " + dumpFile.getAbsolutePath());
                } else {
                    System.err.println("Error during database dump. Exit code: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Failed to create database dump: " + e.getMessage());
            }
        }
    }
}
