package com.ieseljust.psp.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.ieseljust.psp.client.communications.*;

public class App extends Application {

    /*
     * Classe de l'aplicació. Aquesta derivarà de
     * la classe Application, per ser una aplicació JavaFX
     * 
     */

    public void init() {
        // Init inicialitza les variables de l'aplicació
        // En principi aci no cal fer res

    }

    @Override
    public void start(Stage stage) {

        try {

            // Caracteístiques generals de l'aplicació

            // Fitxer de la vista
            String fxml = "mainLayout.fxml";

            // Carreguem la vista
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            Scene scene = new Scene(root);

            // Assignem propietats a l'stage (finestra)
            stage.setTitle("WhatsDAM");
            stage.setMaxHeight(600);
            stage.setMaxWidth(800);
            stage.setResizable(true);

            // Aci també podriem establir la icona de l'aplicació
            // o afegir estils personalitzats.

            var appIcon = new Image("icon.png");
            stage.getIcons().add(appIcon);

            // Estils personalitzats

            // Preparem l'aplicació per finalitzar-la
            // quan es tanque la finestra
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });

            // Assignem l'escena (vista) i mostrem l'stage (finestra)
            stage.setScene(scene);
            stage.show();

            try {
                // Llancem un fil dins la pròpia interfície gràfica
                // Per actualitzar periòdicament la llista d'usuaris

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Runnable updater = new Runnable() {

                            @Override
                            public void run() {
                                // Actualitzem periòdicament
                                // la llista d'usuaris.
                                // L'agafem del model i la passem a la UI
                                mainLayoutController.updateUsuaris(ViewModel.getInstance().getLlistaUsuaris());
                                mainLayoutController.updateMessages(ViewModel.getInstance().getPendingMessages());
                                

                            }
                        };

                        // Amb açò fem que s'execute periòdicament
                        // El Platform.runLater permet llançar un fil
                        // dins la interfície
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                            }

                            // UI update is run on the Application thread
                            Platform.runLater(updater);
                        }
                    }

                });
                // don't let thread prevent JVM shutdown
                thread.setDaemon(true);
                thread.start();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CurrentConfig.init(args[0], args[1]);

        // El ViewModel serà un objecte compartit
        ViewModel vm = new ViewModel();

        // Llancem el fil per escoltar els broadcasts del servidor
        serverListener sl = new serverListener(vm);
        new Thread(sl).start();

        try {
            // CommunicationManaget gestiona les connexions
            // al servidor, per enviar-li missatges i gestionar
            // les respostes. La informació que el servidor
            // enviarà per broadcast es gestiona des del serverListener

            //  Hem d'esperar a que s'establisca el port correctament
            while (!CurrentConfig.connectionReady()){
                System.out.println("Config not ready");
                Thread.sleep(100);
            }
            System.out.println("Config ready");
            communicationManager.connect();

            // Iniciem el cicle de vida
            // de l'aplicació gràfica

            launch(args);
        } catch (communicationManagerException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        } catch(Exception e){
            e.printStackTrace();
        }
        ;

    }

}
