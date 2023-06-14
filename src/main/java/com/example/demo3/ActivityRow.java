package com.example.demo3;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.Duration;

public class ActivityRow {
    private final StringProperty nameProperty;
    private final SimpleObjectProperty<Duration> durationProperty;
    public ActivityRow(String name) {
        nameProperty = new SimpleStringProperty(this, "name");
        nameProperty.setValue(name);
        durationProperty = new SimpleObjectProperty<>(this, "duration");
    }

    public String getName() {
        return nameProperty.getValue();
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }
    public SimpleObjectProperty<Duration> durationProperty() {
        return durationProperty;
    }

    public Duration getDuration() {
        return durationProperty.getValue();
    }
    public void setDuration(Duration duration) {
        durationProperty.setValue(duration);
    }
}