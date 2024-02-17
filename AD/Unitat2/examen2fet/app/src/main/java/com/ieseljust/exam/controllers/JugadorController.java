/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.exam.controllers;

import com.mycompany.entities.Jugador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pc-raul
 */
public class JugadorController {
    private DBController dbController = new DBController();
    private Scanner scanner = new Scanner(System.in);

    public void addJugador(String nick) {
        String sql = "INSERT INTO Jugadors (nick, dataRegistre) VALUES (?, ?)";
        try (Connection connection = dbController.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbController.closeConnection();
        }
    }

    public void deleteJugador(int id) {
        String sql = "DELETE FROM Jugadors WHERE id = ?";
        try (Connection connection = dbController.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbController.closeConnection();
        }
    }

    public void updateJugador(int id, String newnick) {
        String sql = "UPDATE Jugadors SET nick = ? WHERE id = ?";
        try (Connection connection = dbController.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newnick);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbController.closeConnection();
        }
    }

    public void addListofJugadors(int x) {
        ArrayList<Jugador> jugadors = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            System.out.println("Introduce el nick del jugador " + (i + 1) + ":");
            String nick = scanner.nextLine();
            Jugador jugador = new Jugador();
            jugador.setNick(nick);
            jugadors.add(jugador);
        }

        for (Jugador jugador : jugadors) {
            addJugador(jugador.getNick());
        }

        scanner.close(); // Cerrar el scanner después de su uso
    }
}
