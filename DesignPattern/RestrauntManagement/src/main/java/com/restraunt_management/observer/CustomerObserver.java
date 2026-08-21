package com.restraunt_management.observer;


import org.springframework.stereotype.Component;

import com.restraunt_management.model.RestrauntOrder;

@Component
public class CustomerObserver implements OrderObserver {

    @Override
    public void update(RestrauntOrder order) {

        System.out.println(
                "CUSTOMER: Order #" +
                order.getId() +
                " status is now " +
                order.getStatus()
        );
    }

	
}