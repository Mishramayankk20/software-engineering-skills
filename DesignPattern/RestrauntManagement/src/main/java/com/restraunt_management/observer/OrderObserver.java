package com.restraunt_management.observer;

import com.restraunt_management.model.RestrauntOrder;

public interface OrderObserver {

    void update(RestrauntOrder order);
}