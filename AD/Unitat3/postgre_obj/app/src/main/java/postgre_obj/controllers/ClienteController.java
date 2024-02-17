package postgre_obj.controllers;

import postgre_obj.entities.Cliente;
import java.util.List;
import java.util.Date;
import java.util.ArrayList; // AsegÃºrate de importar las clases necesarias
public class ClienteController {
     private List<Cliente> clientes;
    private int lastClientId; // Variable para realizar un seguimiento del último ID asignado

    public ClienteController() {
        this.clientes = new ArrayList<>();
        this.lastClientId = 0; // Inicializar el último ID en 0
    }

    public void createCliente(String nombre, String apellido, Date fechaRegistro) {
        Cliente cliente = new Cliente();
        cliente.setId(++lastClientId); // Incrementar el ID y asignarlo al cliente
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setFechaRegistro(fechaRegistro);
        this.clientes.add(cliente);
    }

    public Cliente readCliente(int id) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void updateCliente(int id, String nombre, String apellido, Date fechaRegistro) {
        Cliente cliente = readCliente(id);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFechaRegistro(fechaRegistro);
        }
    }

    public void deleteCliente(int id) {
        Cliente cliente = readCliente(id);
        if (cliente != null) {
            this.clientes.remove(cliente);
        }
    }

    public List<Cliente> getAllClientes() {
        return this.clientes;
    }
}