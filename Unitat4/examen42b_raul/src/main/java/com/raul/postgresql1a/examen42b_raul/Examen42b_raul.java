/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.raul.postgresql1a.examen42b_raul;

/**
 *
 * @author rauls
 */
import java.util.Scanner;

public class Examen42b_raul {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, introduce tu nombre:");
        String name = scanner.nextLine();

        System.out.println("Por favor, introduce tu fecha de contratación (dd/mm/yyyy):");
        String hireDate = scanner.nextLine();

        System.out.println("Por favor, introduce tu dirección (calle, número y bloque):");
        String address = scanner.nextLine();

        System.out.println("Hola " + name + ", has sido contratado el " + hireDate + " y vives en " + address + ".");
    }
}
