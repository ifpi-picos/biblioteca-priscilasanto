package com.biblioteca.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca"; // Verifique se a porta e o nome do banco de dados estão corretos
  private static final String USER = "postgres";
  private static final String PASSWORD = "Aluno520?";

  public static Connection getConnection() throws SQLException {
    try {
      Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
      System.out.println("Banco conectado com sucesso!");
      return connection;
    } catch (SQLException e) {
      System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
      throw e;
    }
  }
}
