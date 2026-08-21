package com.restraunt_management.observer;

import org.springframework.stereotype.Component;

import com.restraunt_management.model.RestrauntOrder;

@Component
public class KitchenObserver implements OrderObserver {

    @Override
    public void update(RestrauntOrder order) {

        System.out.println(
                "KITCHEN: Order #" +
                order.getId() +
                " status changed to " +
                order.getStatus()
        );
    }

	
}