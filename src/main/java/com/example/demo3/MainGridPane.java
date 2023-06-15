package com.example.demo3;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainGridPane extends GridPane {

    public MainGridPane() {
        //поле ввода
        TextField textField = new TextField();
        textField.setPromptText("Активность");
        StringProperty textFieldProperty = textField.textProperty();

        // добавить снизу кнопок пустую таблицу (TableView)
        TableView<StartEndRow> startEndTableView = new TableView<>();
        TableColumn<StartEndRow, String> activityColumn = new TableColumn<>("Активность");
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        activityColumn.prefWidthProperty().bind(startEndTableView.widthProperty().divide(4));
        startEndTableView.getColumns().add(activityColumn);

        TableColumn<StartEndRow, LocalDateTime> startColumn = new TableColumn<>("Старт");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        startColumn.prefWidthProperty().bind(startEndTableView.widthProperty().divide(4));
        startEndTableView.getColumns().add(startColumn);

        TableColumn<StartEndRow, LocalDateTime> endColumn = new TableColumn<>("Конец");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        endColumn.prefWidthProperty().bind(startEndTableView.widthProperty().divide(4));
        startEndTableView.getColumns().add(endColumn);

        TableColumn<StartEndRow, Duration> durationColumn = new TableColumn<>("Длительность");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.prefWidthProperty().bind(startEndTableView.widthProperty().divide(4));
        startEndTableView.getColumns().add(durationColumn);

        ObservableList<StartEndRow> startEndRows = startEndTableView.getItems();

        //кнопка вкл-выкл
        ToggleButton startEndButton = new ToggleButton("Начать");

        /*//таймер на кнопку начать (на потом)
        Timer timerStartEnd = new Timer();
        Label labelTimerStartEnd = new Label();
        timerStartEnd.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                labelTimerStartEnd.setText("Прошло" + i + "сек");
                i++;
            }
        }, 0, 1000);*/


        //таблица для списка и длительности
        TableView<ActivityRow> activityTableView = new TableView<>();
        TableColumn<ActivityRow, String> activityRow = new TableColumn<>("Список активностей");
        activityRow.setCellValueFactory(new PropertyValueFactory<>("name"));
        activityRow.prefWidthProperty().bind(startEndTableView.widthProperty().divide(2));
        activityTableView.getColumns().add(activityRow);
        TableColumn<ActivityRow, String> activityDuration = new TableColumn<>("Длительность");
        activityDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        activityDuration.prefWidthProperty().bind(startEndTableView.widthProperty().divide(3));
        activityTableView.getColumns().add(activityDuration);
        ObservableList<ActivityRow> activityRows = activityTableView.getItems();
        TableView.TableViewSelectionModel<ActivityRow> activityTableViewSelectionModel =
                activityTableView.getSelectionModel();



        //вывод полей в таблицу
        setGridLinesVisible(true);
        add(textField,0,0);

        add(activityTableView, 0,1);

        /* шпаргалка по настройке
            add(startButton,0,2);
            GridPane.setConstraints(startButton,0,2,1,1, HPos.CENTER, VPos.CENTER);
            startButton.setMaxWidth(Double.MAX_VALUE);
            startButton.setMaxHeight(Double.MAX_VALUE);
            GridPane.setHgrow(startButton, Priority.ALWAYS);
            GridPane.setVgrow(startButton, Priority.ALWAYS);

            add(endButton,0,3);
            endButton.setMaxWidth(Double.MAX_VALUE);
            endButton.setMaxHeight(Double.MAX_VALUE);
            GridPane.setHgrow(endButton, Priority.ALWAYS);
            GridPane.setVgrow(endButton, Priority.ALWAYS);

            startButton.setText("Старт");
            // добавть кнопу стоп, которая при нажатии будет добавлять текущее время в startEnd
            endButton.setText("Стоп");
            StackPane.setAlignment(endButton, Pos.BASELINE_LEFT);
            StackPane.setMargin(endButton, new Insets(10, 0, 50, 0));
        */


        add(startEndButton,0,2);
        startEndButton.setMaxWidth(Double.MAX_VALUE);
        startEndButton.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(startEndButton, Priority.ALWAYS);
        GridPane.setVgrow(startEndButton, Priority.ALWAYS);

        add(startEndTableView,1,0, 1, 5);
        startEndTableView.setMaxWidth(Double.MAX_VALUE);
        startEndTableView.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(startEndTableView, Priority.ALWAYS);
        GridPane.setVgrow(startEndTableView, Priority.ALWAYS);


        StartEndController startEndController = new StartEndController();
        SaveLoadController saveLoadController = new SaveLoadController();

        startEndController.setStartEndRows(startEndRows);
        startEndController.setActivityRows(activityRows);
        startEndController.setSaveLoadController(saveLoadController);
        startEndController.setTextFieldProperty(textFieldProperty);
        startEndController.setActivityTableViewSelectionModel(activityTableViewSelectionModel);

        startEndController.setStartEndButton(startEndButton); //передааем кнопку вкл-вык в StartEndController

        saveLoadController.setActivityRows(activityRows);
        saveLoadController.setStartEndRows(startEndRows); // АХТУНГ ТАМ ВНУТРИ ЗАГРУЗКА ИЗ ФАЙЛА



    }

}
