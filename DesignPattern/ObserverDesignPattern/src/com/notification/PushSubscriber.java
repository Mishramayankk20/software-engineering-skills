package com.notification;

public class PushSubscriber implements Observer {
    private final String deviceId;

    public PushSubscriber(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void update(String message) {
        System.out.println("Push to device " + deviceId + ": " + message);
    }
}
