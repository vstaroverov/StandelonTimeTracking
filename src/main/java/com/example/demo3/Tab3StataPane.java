package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Priority;

public class Tab3StataPane extends GridPane {
        public  Tab3StataPane() {

                //собираем данные для пирога
                ObservableList<PieChart.Data> tab3StataPaneContent = FXCollections.observableArrayList(
                        new PieChart.Data("Программирование", 13),
                        new PieChart.Data("Спорт", 25),
                        new PieChart.Data("Бег", 10));

                //это конструктор пирога
        PieChart tab3StataChart = new PieChart(tab3StataPaneContent);

        tab3StataChart.setMaxHeight(Double.MAX_VALUE);
        tab3StataChart.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(tab3StataChart, Priority.ALWAYS);
        GridPane.setVgrow(tab3StataChart, Priority.ALWAYS);


                tab3StataChart.setTitle("Статистика");

                add(tab3StataChart, 0, 0);
    }
}
//добавить поле для вывода пирога
//добавить пирог
//получить данные
//заполнить пирог