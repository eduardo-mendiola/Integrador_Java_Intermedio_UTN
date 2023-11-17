package com.integrador.SisRepInc.dataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
    public static void main(String[] args) {
        try (Connection conexion = ConexionDB2.obtenerConexion();
             Statement statement = conexion.createStatement()) {

            String consulta = "SELECT * FROM tu_tabla";
            try (ResultSet resultSet = statement.executeQuery(consulta)) {
                while (resultSet.next()) {
                    // Procesar resultados
                    String columna1 = resultSet.getString("columna1");
                    int columna2 = resultSet.getInt("columna2");

                    // Hacer algo con los datos
                    System.out.println(columna1 + ", " + columna2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

