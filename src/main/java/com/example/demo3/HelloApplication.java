package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Программа");
        //создать панель табов
        TabPane tabPane = new TabPane();
        //создать таб для маингрид пайн
        Tab tab1 = new Tab();
        tab1.setText("Учет");
        Tab tab2 = new Tab();
        tab2.setText("Идеи");
        // По аналогии с MainGridPane создать новую, пустую, панель унаследованную
        // от GridPane в отдельно классе, и установить её во второй таб
        Tab tab3 = new Tab();
        tab3.setText("Стата");
        //вкладка для теста
        Tab tab4 = new Tab();
        tab4.setText("Для Теста");
        //передать в таб маин грид пейн
        MainGridPane mainGridPane = new MainGridPane();
        tab1.setContent(mainGridPane);
        //передать таб в панель табов
        tabPane.getTabs().add(tab1);
        //передать паенль табов в сцену вместо маингридпей

        //вкладка 2
        Tab2GridPane tab2GridPane = new Tab2GridPane();
        tab2.setContent(tab2GridPane);
        tabPane.getTabs().add(tab2);

        //вкладка 3, выводим пирог
        Tab3StataPane tab3StataChart = new Tab3StataPane();
        tab3.setContent(tab3StataChart);
        tabPane.getTabs().add(tab3);

        //вкладка 4, для теста
        Tab4TestPane tab4TestPane = new Tab4TestPane();
        tab4.setContent(tab4TestPane);
        tabPane.getTabs().add(tab4);

        primaryStage.setScene(new Scene(
                tabPane, 800, 600
        ));
        primaryStage.show();
    }
}