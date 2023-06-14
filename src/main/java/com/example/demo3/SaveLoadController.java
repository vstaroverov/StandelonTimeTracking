package com.example.demo3;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDateTime;

public class SaveLoadController {

    private ObservableList<StartEndRow> startEndRows;

    private ObservableList<ActivityRow> activityRows;
    public void setStartEndRows(ObservableList<StartEndRow> startEndRows) {
        this.startEndRows = startEndRows;
        loadFromFile();
        startEndRows.addListener(this::saveToFileAfterListChange);
    }

    public void setActivityRows(ObservableList<ActivityRow> activityRows) {
        this.activityRows = activityRows;
    }

    private void saveToFileAfterListChange(ListChangeListener.Change<? extends StartEndRow> change) {
        saveToFile();
    }

    public void saveToFile() {
        // что это PrintWriter writer = new PrintWriter(new File("test.txt"))
        // writer.println("Hello World");
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream("activities.txt"))
        ) {
            startEndRows.forEach(row -> {
                try {
                    objectOutputStream.writeObject(row.getEventName());
                    objectOutputStream.writeObject(row.getStartDateTime());
                    objectOutputStream.writeObject(row.getEndDateTime());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("ЙА СХОРОНИЛ");
        } catch (IOException e) {
            System.out.println("НИСАХРАНИНННОООААА! АААА!!! УУУУ! ЫЫЫ");
        }
    }
    private void loadFromFile() {
        try (ObjectInputStream  objectInputStream =
                     new ObjectInputStream(new FileInputStream("activities.txt"))
        ) {
            do {
                String name = (String)objectInputStream.readObject();
                LocalDateTime start = (LocalDateTime)objectInputStream.readObject();
                StartEndRow row = new StartEndRow(name, start);
                LocalDateTime end = (LocalDateTime)objectInputStream.readObject();
                row.setEndDateTime(end);
                int available = objectInputStream.available();
                startEndRows.add(row);
            } while (objectInputStream.available() == 0);
            //System.out.println("ЙА СХОРОНИЛ");
        } catch (IOException e) {
            //System.out.println("НИСАХРАНИНННОООААА! АААА!!! УУУУ! ЫЫЫ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // заполним activityRows уникальными значениями из startEndRows
        startEndRows.stream().map(StartEndRow::getEventName).distinct().map(ActivityRow::new).forEach(activityRows::add);
    }
}
