package projecte;

import projecte.controllers.PostsController;
import projecte.controllers.UsersController;
import projecte.controllers.EventsController;
import projecte.controllers.MessageController;
import projecte.entities.Posts;
import projecte.entities.User;
import projecte.entities.Event;
import projecte.entities.Message;
import java.util.List;
import java.util.Scanner;
import projecte.controllers.ProfileController;
import projecte.entities.Profile;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Users");
            System.out.println("2. Events");
            System.out.println("3. Messages");
            System.out.println("4. Posts");
            System.out.println("5. Profiles");
            System.out.println("0. Salir");

            int option = scanner.nextInt();

            if (option == 0) {
                System.out.println("Saliendo...");
                break;
            }

            switch (option) {
                case 1:
                    performUserOperations();
                    break;
                case 2:
                    performEventOperations();
                    break;
                case 3:
                    performMessageOperations();
                    break;
                case 4:
                    performPostOperations();
                    break;
                case 5:
                    performProfileOperations();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void performUserOperations() {
        UsersController usersController = new UsersController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una operación para Users:");
        System.out.println("1. Listar todos los usuarios");
        System.out.println("2. Insertar nuevo usuario");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Borrar usuario");

        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1:
                List<User> userList = usersController.getAllUsers();
                userList.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese el nombre del nuevo usuario:");
                String userName = scanner.next();
                System.out.println("Ingrese la contraseña del nuevo usuario:");
                String userPassword = scanner.next();
                User newUser = new User(userName, userPassword);
                usersController.insert_user(newUser);
                break;
            case 3:
                System.out.println("Ingrese el ID del usuario a actualizar:");
                long userIdToUpdate = scanner.nextLong();
                usersController.updateUser(userIdToUpdate);
                break;
            case 4:
                System.out.println("Ingrese el ID del usuario a borrar:");
                long userIdToDelete = scanner.nextLong();
                usersController.delete_user(userIdToDelete);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        // Refrescar la lista después de realizar la operación
        if (userOption != 3) {
            List<User> updatedUserList = usersController.getAllUsers();
            updatedUserList.forEach(System.out::println);
        }
    }

    private static void performEventOperations() {
        EventsController eventsController = new EventsController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una operación para Events:");
        System.out.println("1. Listar todos los eventos");
        System.out.println("2. Insertar nuevo evento");
        System.out.println("3. Actualizar evento");
        System.out.println("4. Borrar evento");

        int eventOption = scanner.nextInt();

        switch (eventOption) {
            case 1:
                List<Event> eventList = eventsController.getAllEvents();
                eventList.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese el nombre del nuevo evento:");
                String eventName = scanner.next();
                Event newEvent = new Event(eventName);
                eventsController.insertEvent(newEvent);
                break;
            case 3:
                System.out.println("Ingrese el ID del evento a actualizar:");
                long eventIdToUpdate = scanner.nextLong();
                break;
            case 4:
                System.out.println("Ingrese el ID del evento a borrar:");
                long eventIdToDelete = scanner.nextLong();
                eventsController.deleteEvent(eventIdToDelete);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void performMessageOperations() {
        MessageController messageController = new MessageController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una operación para Messages:");
        System.out.println("1. Listar todos los mensajes");
        System.out.println("2. Insertar nuevo mensaje");
        System.out.println("3. Actualizar mensaje");
        System.out.println("4. Borrar mensaje");

        int messageOption = scanner.nextInt();

        switch (messageOption) {
            case 1:
                List<Message> messageList = messageController.getAllMessages();
                messageList.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese el texto del nuevo mensaje:");
                String messageText = scanner.next();
                Message newMessage = new Message(messageText);
                messageController.insertMessage(newMessage);
                break;
            case 3:
                System.out.println("Ingrese el ID del mensaje a actualizar:");
                long messageIdToUpdate = scanner.nextLong();
                // Lógica para actualizar mensajes
                break;
            case 4:
                System.out.println("Ingrese el ID del mensaje a borrar:");
                long messageIdToDelete = scanner.nextLong();
                messageController.deleteMessage(messageIdToDelete);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void performPostOperations() {
        PostsController postsController = new PostsController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una operación para Posts:");
        System.out.println("1. Listar todos los posts");
        System.out.println("2. Insertar nuevo post");
        System.out.println("3. Actualizar post");
        System.out.println("4. Borrar post");

        int postOption = scanner.nextInt();

        switch (postOption) {
            case 1:
                List<Posts> postsList = postsController.findAllPosts();
                postsList.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese el título del nuevo post:");
                String postTitle = scanner.next();
                Posts newPost = new Posts(postTitle);
                postsController.insertPost(newPost);
                break;
            case 3:
                System.out.println("Ingrese el ID del post a actualizar:");
                long postIdToUpdate = scanner.nextLong();
                // Lógica para actualizar posts
                break;
            case 4:
                System.out.println("Ingrese el ID del post a borrar:");
                long postIdToDelete = scanner.nextLong();
                postsController.deletePost(postIdToDelete);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void performProfileOperations() {
        ProfileController profileController = new ProfileController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una operación para Profiles:");
        System.out.println("1. Listar todos los perfiles");
        System.out.println("2. Insertar nuevo perfil");
        System.out.println("3. Actualizar perfil");
        System.out.println("4. Borrar perfil");

        int profileOption = scanner.nextInt();

        switch (profileOption) {
            case 1:
                List<Profile> profileList = profileController.getAllProfiles();
                profileList.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese la bio del nuevo perfil:");
                String bio = scanner.next();
                Profile newProfile = new Profile(bio);
                profileController.insertProfile(newProfile);
                break;
            case 3:
                System.out.println("Ingrese el ID del perfil a actualizar:");
                long profileIdToUpdate = scanner.nextLong();
                System.out.println("Ingrese la nueva bio del perfil:");
                String newBio = scanner.next();
                profileController.updateProfile(profileIdToUpdate, newBio);
                break;
            case 4:
                System.out.println("Ingrese el ID del perfil a borrar:");
                long profileIdToDelete = scanner.nextLong();
                profileController.deleteProfile(profileIdToDelete);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

}
