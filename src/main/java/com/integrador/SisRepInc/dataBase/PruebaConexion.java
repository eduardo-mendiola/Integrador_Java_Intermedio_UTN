package com.integrador.SisRepInc.dataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        try {
            // Obtener conexión
            Connection conexion = ConexionDB2.obtenerConexion();

            // Verificar si la conexión es exitosa
            if (conexion != null) {
                System.out.println("¡Conexión exitosa!");
                conexion.close(); // Cerrar la conexión después de la prueba
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

