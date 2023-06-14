package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Идеи для улучшение Backlog
 * 1. Умный список для выбора активности (готово)
 * 2. Пироговая диаграмма для разделения дня на активности
 * 3. стобики-диаграмма прогресса
 * 4. Записная книжка для идей с параметрами и фильтром
 * 5. Файл для хранения и подгрузки данных (готово)
 * 6. Сохранять местополодение и размер окна программы
 * 7. Поле активность обязательно к заполнению
 * 8. Автоматическая остановка при закрытии программы
 * 9. Баг при закрытии нет даты завершения
 * 10. Формат даты и время нормальный
 * 11. Новые записи вверху - старые внизу
 * 12. Подтверждение закрытие и сохранение результата
 * 13. Кнопку удаления активности из списка
 * 14. Проверка ввода текста где есть большая буква (прИвет и привет разные)
 * 15. Несколько дел одновременно
 * 16. Добавить вкладку с идеями и статистикой
 */
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

        //вкладка 3
        tabPane.getTabs().add(tab3);

        primaryStage.setScene(new Scene(
                tabPane, 800, 600
        ));
        primaryStage.show();
    }
}