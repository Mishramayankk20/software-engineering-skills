package com.notification;

public class SmsSubscriber implements Observer {
    private final String phoneNumber;

    public SmsSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String message) {
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}
