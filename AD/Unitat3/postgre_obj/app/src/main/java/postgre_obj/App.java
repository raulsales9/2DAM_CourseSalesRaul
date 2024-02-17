package postgre_obj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import postgre_obj.controllers.ClienteController;
import postgre_obj.entities.Cliente;

public class App {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Clientes");
            System.out.println("0. Salir");

            int option = scanner.nextInt();

            if (option == 0) {
                System.out.println("Saliendo...");
                break;
            }

            switch (option) {
                case 1:
                    performClienteOperations(clienteController, scanner);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void performClienteOperations(ClienteController clienteController, Scanner scanner) throws ParseException {
        System.out.println("Seleccione una operación para Clientes:");
        System.out.println("1. Listar todos los clientes");
        System.out.println("2. Insertar nuevo cliente");
        System.out.println("3. Actualizar cliente");
        System.out.println("4. Borrar cliente");

        int clientOption = scanner.nextInt();

        switch (clientOption) {
            case 1:
                List<Cliente> clientList = clienteController.getAllClientes();
                clientList.forEach(System.out::println);
                break;
            case 2:
                insertarNuevoCliente(clienteController, scanner);
                break;
            case 3:
                actualizarCliente(clienteController, scanner);
                break;
            case 4:
                borrarCliente(clienteController, scanner);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        // Refrescar la lista después de realizar la operación
        if (clientOption != 3) {
            List<Cliente> updatedClientList = clienteController.getAllClientes();
            updatedClientList.forEach(System.out::println);
        }
    }

    private static void insertarNuevoCliente(ClienteController clienteController, Scanner scanner) throws ParseException {
        System.out.println("Ingrese el nombre del nuevo cliente:");
        String clientName = scanner.next();
        System.out.println("Ingrese el apellido del nuevo cliente:");
        String clientSurname = scanner.next();
        System.out.println("Ingrese la fecha de registro del nuevo cliente (formato yyyy-MM-dd):");
        String date = scanner.next();
        Date clientDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        // Crear un nuevo Cliente y agregarlo a la lista
        clienteController.createCliente(clientName, clientSurname, clientDate);
    }

    private static void actualizarCliente(ClienteController clienteController, Scanner scanner) throws ParseException {
        System.out.println("Ingrese el ID del cliente a actualizar:");
        int clientIdToUpdate = scanner.nextInt();
        System.out.println("Ingrese el nuevo nombre del cliente:");
        String newName = scanner.next();
        System.out.println("Ingrese el nuevo apellido del cliente:");
        String newSurname = scanner.next();
        System.out.println("Ingrese la nueva fecha de registro del cliente (formato yyyy-MM-dd):");
        String newDate = scanner.next();
        Date newClientDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);

        // Actualizar la información del Cliente
        clienteController.updateCliente(clientIdToUpdate, newName, newSurname, newClientDate);
    }

    private static void borrarCliente(ClienteController clienteController, Scanner scanner) {
        System.out.println("Ingrese el ID del cliente a borrar:");
        int clientIdToDelete = scanner.nextInt();

        // Eliminar el Cliente de la lista
        clienteController.deleteCliente(clientIdToDelete);
    }
}
