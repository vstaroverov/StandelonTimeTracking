package com.example.demo3;

import javafx.animation.AnimationTimer;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.HashMap;

public class Tab4TestPane extends GridPane {

    public Tab4TestPane() {
        TextArea textArea = new TextArea();
        add(textArea, 0, 0);
        textArea.setMaxHeight(100);
        textArea.setMaxWidth(100);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane.setVgrow(textArea, Priority.ALWAYS);



    }
}
