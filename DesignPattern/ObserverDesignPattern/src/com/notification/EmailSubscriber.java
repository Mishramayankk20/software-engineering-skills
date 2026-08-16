package com.notification;

public class EmailSubscriber implements Observer {
    private final String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Email to " + email + ": " + message);
    }
}
