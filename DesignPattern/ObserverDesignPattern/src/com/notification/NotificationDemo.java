package com.notification;

public class NotificationDemo {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        Observer emailUser = new EmailSubscriber("mishramayankk20@gmail.com");
        Observer smsUser = new SmsSubscriber("+91-9999234884");
        Observer pushUser = new PushSubscriber("Device-mayankk20");

        service.subscribe(emailUser);
        service.subscribe(smsUser);
        service.subscribe(pushUser);

        service.notifyAllObservers("Your order has been shipped!");

        service.unsubscribe(smsUser);
        service.notifyAllObservers("Your order has been delivered!");
    }
}
