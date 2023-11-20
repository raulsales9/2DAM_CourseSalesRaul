package projecte;

import java.util.Arrays;
import java.util.List;
import projecte.controllers.UsersController;
import projecte.entities.User;

public class App {
    public static void main(String[] args) {
        UsersController usersController = new UsersController();

        // Insertar un nuevo usuario
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setPassword("12345");
        newUser.setPhone(123456789);

        usersController.insert_user(newUser);

        // Obtener todos los usuarios después de la inserción
        List<User> userList = usersController.getAllUsers();
        System.out.println("Lista de Usuarios después de la inserción:");
        for (User user : userList) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getName() + ", Email: " + user.getEmail());
        }

        // Actualizar el usuario con ID 1
        usersController.updateUser(1);

        // Obtener todos los usuarios después de la actualización
        userList = usersController.getAllUsers();
        System.out.println("Lista de Usuarios después de la actualización:");
        for (User user : userList) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getName() + ", Email: " + user.getEmail());
        }

        // Eliminar el usuario con ID 1
        User userToDelete = new User();
        userToDelete.setId(1L);
        usersController.delete_user(userToDelete);

        // Obtener todos los usuarios después de la eliminación
        userList = usersController.getAllUsers();
        System.out.println("Lista de Usuarios después de la eliminación:");
        for (User user : userList) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getName() + ", Email: " + user.getEmail());
        }
    }
}
