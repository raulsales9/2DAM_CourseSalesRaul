/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.sql.Connection;
/**
 *
 * @author pc-raul
 */
public class MainActivity {

    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager("tu_servidor_mysql", "tu_puerto_mysql", "tu_usuario_mysql", "tu_contraseña_mysql");
        Connection connection = connectionManager.connectDBMS();

        if (connection != null) {
            System.out.println("Conexión a la base de datos exitosa.");
            // Realiza tus operaciones de base de datos aquí
        } else {
            System.out.println("Fallo en la conexión a la base de datos.");
        }
    }
}
