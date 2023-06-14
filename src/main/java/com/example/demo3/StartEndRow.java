package com.example.demo3;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class StartEndRow {
    private final StringProperty eventNameProperty;
    private final SimpleObjectProperty<LocalDateTime> startDateTimeProperty;
    private final SimpleObjectProperty<LocalDateTime> endDateTimeProperty;
    private final SimpleObjectProperty<Duration> durationProperty;

    public StartEndRow(String eventName, LocalDateTime startDateTime) {
        eventNameProperty = new SimpleStringProperty(this, "eventName");
        eventNameProperty.setValue(eventName);
        startDateTimeProperty = new SimpleObjectProperty<>(this, "startDateTime");
        startDateTimeProperty.setValue(startDateTime);
        endDateTimeProperty = new SimpleObjectProperty<>(this, "endDateTime");
        durationProperty = new SimpleObjectProperty<>(this, "duration");
    }
    public LocalDateTime getStartDateTime() {
        return startDateTimeProperty.getValue();
    }
    public SimpleObjectProperty<LocalDateTime> startDateTimeProperty() {
        return startDateTimeProperty;
    }
    public SimpleObjectProperty<Duration> durationProperty() {
        return durationProperty;
    }

    public Duration getDuration() {
        return durationProperty.getValue();
    }

    public String getEventName() {
        return eventNameProperty.getValue();
    }
    public StringProperty eventNameProperty() {
        return eventNameProperty;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        if (endDateTime == null) return;
        endDateTimeProperty.setValue(endDateTime);
        durationProperty.setValue(Duration.between(getStartDateTime(), getEndDateTime()));
    }

    public LocalDateTime getEndDateTime() {
        return endDateTimeProperty.getValue();
    }

    public SimpleObjectProperty<LocalDateTime> endDateTimeProperty() {
        return endDateTimeProperty;
    }
}
