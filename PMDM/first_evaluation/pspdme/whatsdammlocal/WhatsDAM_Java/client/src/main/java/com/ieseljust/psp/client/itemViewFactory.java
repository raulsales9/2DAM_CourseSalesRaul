package com.ieseljust.psp.client;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class itemViewFactory implements Callback<ListView<Message>, ListCell<Message>> {
   
    @Override
    public ListCell<Message> call(ListView<Message> param) {
        return new itemView<Message>(2);
    }
}