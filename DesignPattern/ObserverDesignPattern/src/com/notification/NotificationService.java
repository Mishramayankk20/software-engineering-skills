package com.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
