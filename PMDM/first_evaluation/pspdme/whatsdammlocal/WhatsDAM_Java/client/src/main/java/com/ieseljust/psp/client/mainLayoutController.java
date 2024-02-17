package com.ieseljust.psp.client;

import java.util.ArrayList;

import com.ieseljust.psp.client.communications.communicationManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class mainLayoutController {
    /*
     * Aquesta classe és carregada des de l'XML de la interfície, i s'encarrega
     * de la seua gestió (sería la vista)
     * 
     */

    // Components gràfics de la UI
    @FXML
    private Button sendBt; // Botó d'enviar
    @FXML
    private ListView<Message> messageList; // Llista de missatges
    @FXML
    private ListView<String> userList; // Llista d'usuaris
    @FXML
    private TextArea message; // Missatge

    // Definim un parell de llistes com a observables, per als usuaris i missatges
    public static ObservableList<Message> llistaMissatges = FXCollections.observableArrayList();
    public static ObservableList<String> llistaUsuaris = FXCollections.observableArrayList();

    // Mètode que es llança en crear la finestra
    @FXML
    void initialize() {
        // Per establir el Factory que crea els items de misstges (defineix l'aspecte
        // dels missatges)
        messageList.setCellFactory(new itemViewFactory());
        // Estableix els items de la llista de missatge
        messageList.setItems(llistaMissatges);
        // Estableix els items de la llista d'usuaris
        userList.setItems(llistaUsuaris);

        // Gestio d'events: Clic en el botó d'enviar
        sendBt.setOnMouseClicked((event) -> enviarMisstge());

        // Enviem el missatge també quan es fa clic en ENTER
        message.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                enviarMisstge();

        });

    }

    public static void updateUsuaris(ArrayList<String> llista) {

        try {
            llistaUsuaris.clear();
            for (String item : llista)
                llistaUsuaris.add(item);
            // Si volem mostrar la llista d'usuaris: System.out.println(llistaUsuaris.toString());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void updateMessages(ArrayList<Message> llista) {

        try {
            for (Message item : llista)
                llistaMissatges.add(item);
            
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private void enviarMisstge() {

        // Envia el missatge si no és en blanc i neteja l'àrea de text
        
        if (!message.getText().equals("\n")) {

            Message msg = new Message(CurrentConfig.username(), message.getText());
            // Enviem el missatge al servidor
            communicationManager.sendMessage(msg);
            
        }

        message.setText("");

    }

}
