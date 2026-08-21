package com.restraunt_management.observer;


import org.springframework.stereotype.Component;

import com.restraunt_management.model.RestrauntOrder;

@Component
public class WaiterObserver implements OrderObserver {

    @Override
    public void update(RestrauntOrder order) {

        System.out.println(
                "WAITER: Order #" +
                order.getId() +
                " status changed to " +
                order.getStatus()
        );
    }
}