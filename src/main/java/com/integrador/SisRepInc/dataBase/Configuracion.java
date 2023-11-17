package com.integrador.SisRepInc.dataBase;

import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    private static final String PROPERTIES_FILE = "config.properties";

    public static Properties cargarPropiedades() {
        Properties propiedades = new Properties();
        try (InputStream input = Configuracion.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Lo siento, no pude encontrar el archivo " + PROPERTIES_FILE);
                return propiedades;
            }
            // cargar un conjunto de propiedades desde el archivo de configuración
            propiedades.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propiedades;
    }
}

