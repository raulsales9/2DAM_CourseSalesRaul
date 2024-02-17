package com.ieseljust.psp.client;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class itemView<T> extends ListCell<Message> {
// Aquesta classe representa la implementació
// gràfica d'un missatge per afegir al ListView 
// de missatges.

    @FXML
    private Label user;

    @FXML
    private Label content;

    @FXML
    private VBox bubble;

    @FXML
    private VBox bubbleContainer;

    public itemView(int a) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getClassLoader().getResource("itemView.fxml"));

            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            content.setText(item.getContent());
            user.setText(item.getUser());
            
            // Si el missatge és del propi usuari, el pinta de verd
            System.out.println(CurrentConfig.username());
            if (item.getUser().equals(CurrentConfig.username())) {
                bubble.setStyle("-fx-background-color: #dcf8c6; -fx-border-color:c7c1ba; -fx-border-radius:3");
                bubbleContainer.setStyle("-fx-alignment: center-right;");
            }
            
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
