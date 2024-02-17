/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.entities.Jugador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author pc-raul
 */
public class CrudController {
    private Connection conexion;

    public CrudController(Connection conexion) {
        this.conexion = conexion;
    }
    public void addJugador(String nick){
        String insertQuery = "INSERT INTO jugador (nick, dataRegistre) VALUES (?, ?)";
        
        //instanciem un jugador
        //Jugador jugador = new Jugador();
        //jugador.setNick(nick);
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery);
            preparedStatement.setString(1, nick);
        
            int rows = preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deleteJugador(int id) {
        String deleteQuery = "DELETE FROM jugador WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateJugador(int id, String newNick) {
        String updateQuery = "UPDATE jugador SET nick = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(updateQuery);
            preparedStatement.setString(1, newNick);
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //no es convinguent utilitzar resultsets per a questa tasca, sino la llista que cita l'enunciat
    public void addListOfJugadors(int x) {
        Scanner scanner = new Scanner(System.in);
        List<String> nombresJugadores = new ArrayList<>();

        for (int i = 1; i <= x; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            String nick = scanner.nextLine();
            nombresJugadores.add(nick);
        }

        insertJugadoresAtEnd(nombresJugadores);
    }
     
    private void insertJugadoresAtEnd(List<String> nombresJugadores) {
        for (String nick : nombresJugadores) {
            addJugador(nick);
        }
    }
     
     
}
