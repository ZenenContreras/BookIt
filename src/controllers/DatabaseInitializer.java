package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader("data/schema.sql"))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            // Ejecuta el script SQL completo
            statement.execute(sql.toString());
            System.out.println("Database initialized using schema.sql.");
        } catch (IOException | RuntimeException | SQLException e) {
            throw new RuntimeException("Error initializing database from script", e);
        }
    }
}
