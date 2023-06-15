package com.example.demo3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Tab2GridPane extends GridPane {
    public Tab2GridPane() {
        // это конструктор ---> работает после создания объекта через new Tab2GridPane();
        TextArea textArea = new TextArea();
        // передача текстовой зоны в панель Tab2GridPane (используется метод, унаследованный от GridPane)
        add(textArea, 0, 0);
        textArea.setMaxHeight(Double.MAX_VALUE);
        textArea.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        loadFromFile(textArea);
        //слушатель изменений текст арии
        textArea.textProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                // сюда приходят сообщения о изменении текста
            }
        });
        textArea.textProperty().addListener(this::changedTab2textArea);
    }
    //Это метод сохранение в файл. слушатель
    public void changedTab2textArea(ObservableValue<? extends String> observableValue, String s, String t1) {
        // сюда тоже приходят сообщения о изменении текста,
        // если ссылку на метод передать в слушатели textProperty
        System.out.println("изменения зафиксированы");
        // сохранение в файл
        //какой текст нам нужно сохранить? текст из текст ариа
        //если в данном методе доступ к этому текту? есть
        //какие параметры приходят в этот метод от свойства с тексом текст ариа - t1
        try (var fw = new FileWriter("idea.txt")) {
            fw.write(t1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFromFile(TextArea textArea) {
         //если у нас в методе доступ к тому куда мы отправим считанный из файла текст? - нет
         //как можно получить этот доступ?
         //можно ли в этот метод передать переменную текс ариа? как передать эту переменну?
        //как реализуется передача параметров в метод
        //что именно мы хотим получить в этот метод, чтобы потом сохранить? - TextArea
        //читай файл и пиши в TextArea
        try (Stream<String> lines = Files.lines(Path.of("idea.txt"))) {
            textArea.textProperty().setValue(lines.map(s -> s + "\n").reduce("", String::concat));
        } catch (IOException ex) {
            //
        }
    }
}







