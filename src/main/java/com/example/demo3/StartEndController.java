package com.example.demo3;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.time.LocalDateTime;
import java.util.Locale;

// это класс объекта хранения данных
// дата-время начала, дата-время конца, строка названия события

public class StartEndController {

    //кнопка вкл-вык
    private ToggleButton startEndButton;

    private SaveLoadController saveLoadController;

    private StringProperty textFieldProperty;

    private TableView.TableViewSelectionModel<ActivityRow> activityTableViewSelectionModel;
    private ObservableList<StartEndRow> startEndRows;
    private StartEndRow startEndRow;
    private String eventName;
    //передаем табличу списка и длительности
    private ObservableList<ActivityRow> activityRows;
    private ActivityRow activityRow;

    public void setSaveLoadController(SaveLoadController saveLoadController) {
        this.saveLoadController = saveLoadController;
    }

    public void setTextFieldProperty(StringProperty textFieldProperty) {
        this.textFieldProperty = textFieldProperty;
        textFieldProperty.addListener(this::handleTextChanged);
    }

    public void setActivityTableViewSelectionModel(TableView.TableViewSelectionModel<ActivityRow> activityTableViewSelectionModel) {
        this.activityTableViewSelectionModel = activityTableViewSelectionModel;
        activityTableViewSelectionModel.selectedItemProperty().addListener(this::selectionChange);
    }

    public void selectionChange(
            ObservableValue<? extends ActivityRow> observableValue,
            ActivityRow activityRow, ActivityRow t1
    ) {
        textFieldProperty.setValue(t1.getName());
    }

    public void handleStart() {
        System.out.println("handling start");
        if (startEndRow == null) {
            System.out.println("starting " + eventName);
            //логика кнопки для записи в список (1. Берем значение из TextFiled
            //2. Проверяем есть в списке или нет
            //3. Если нет добавляем
            //что мы ищем? мы ищем значение из поля Активность eventName
            //где мы ищем? мы ищем в activityRows
            //что такое activityRows? это список
            // Это список чего (с чем)? это список со строками таблицы
            //Вопрос. можем ли мы впринципе найти название активности (строку)
            // в списке с объектами строк таблицы
            //ответ: берем искомое значение из TextFiled, берем таблицу, определяем кол-во строк,
            //проверяем значение name поля сроки на совпадение с значением из TextFiled
            int eventNameInActivityRowsNameIndex = -1;
            int activityRowsCount = activityRows.size();

            for (int i = 0; i < activityRowsCount; i++) {
                ActivityRow row = activityRows.get(i);
                String name = row.getName();
                if (name.equalsIgnoreCase(eventName)) {
                    eventNameInActivityRowsNameIndex = i;
                    break;
                }
            }
            // То эе самое что в for через библиотеку Stream
            //boolean isActivityRowsContainEventName = activityRows.stream().map(ActivityRow::getName).anyMatch(eventName::equals);

            String normalizedEventName =
                    eventName.substring(0,1).toUpperCase(Locale.ROOT)
                    + eventName.substring(1).toLowerCase(Locale.ROOT);
            if (eventNameInActivityRowsNameIndex == -1) {
                // не нашли - надо создать строку, заполнить, и добавить в activityRows
                ActivityRow newRow = new ActivityRow(normalizedEventName);
                activityRows.add(newRow);
            } else {
                // нашли - пока ничего не делать

            }

            //что будет в индекс, если значение не найдено? Ответ: -1
            startEndRow = new StartEndRow(normalizedEventName, LocalDateTime.now());
            startEndRows.addAll(startEndRow);
        } else {
            System.out.println("already started");
        }
    }

    public void handleEnd() {
        System.out.println("handling end");
        if (startEndRow != null) {
            System.out.println("ending " + startEndRow.getEventName());
            startEndRow.setEndDateTime(LocalDateTime.now());
            startEndRow = null;
            saveLoadController.saveToFile();
        } else {
            System.out.println("not started");
        }
    }

    public void handleTextChanged(
            ObservableValue<? extends String> observableValue,
            String oldText, String newText
    ) {
        eventName = newText;
    }

    public void setStartEndRows(ObservableList<StartEndRow> startEndRows) {
        this.startEndRows = startEndRows;
    }
    //установка строк содержимого для таблицы список и длительность
    public void setActivityRows(ObservableList<ActivityRow> activityRows) {
        this.activityRows = activityRows;
    }

    //метод в который можно передать кнопку вкл-выкл
    public void setStartEndButton(ToggleButton startEndButton) {
        this.startEndButton = startEndButton;
        startEndButton.selectedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                        // --->
                    }
                }
        );

        startEndButton.selectedProperty().addListener(
                this::startEndButtonChanged
        );

        startEndButton.setDisable(true);
        textFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                startEndButton.setDisable(t1 == null || t1.equals(""));
            }
        });
    }

    public void startEndButtonChanged(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newState) {
        /// ->>> когда состояние кнопки меняется вызывается этот метод
        if (newState) {
            // Новое состояние - включено
            // Сначала создать запись в таблицу
            handleStart();
            // Потом перекрасить и поменять текст
            startEndButton.textProperty().setValue("Закончить");
            /*startEndButton.backgroundProperty().setValue(new Background(new BackgroundFill(
                    Color.RED, CornerRadii.EMPTY, Insets.EMPTY
            )));*/
            // Совсем потом обновлять текст с таймером
        } else {
            // Новое состояние - выключено
            handleEnd();
            startEndButton.textProperty().setValue("Начать");
            /*startEndButton.backgroundProperty().setValue(new Background(new BackgroundFill(
                    Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY
            )));*/
        }
    }



}
