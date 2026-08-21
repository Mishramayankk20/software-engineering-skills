package com.restraunt_management.service;

import org.springframework.stereotype.Service;

import com.restraunt_management.decorator.BillCalculator;
import com.restraunt_management.decorator.PackingChargeDecorator;
import com.restraunt_management.decorator.ServiceChargeDecorator;
import com.restraunt_management.decorator.TaxDecorator;
import com.restraunt_management.model.MenuItem;
import com.restraunt_management.model.OrderItem;
import com.restraunt_management.model.OrderStatus;
import com.restraunt_management.model.RestrauntOrder;
import com.restraunt_management.model.RestrauntTable;
import com.restraunt_management.observer.OrderObserver;
import com.restraunt_management.strategy.DiscountStrategy;
import com.restraunt_management.strategy.DiscountStrategyFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final Map<Long, RestrauntOrder> orders =
            new HashMap<>();

    private final AtomicLong idGenerator =
            new AtomicLong(1);

    private final MenuService menuService;
    private final TableService tableService;
    private final DiscountStrategyFactory discountFactory;

    private final List<OrderObserver> observers;

    public OrderService(
            MenuService menuService,
            TableService tableService,
            DiscountStrategyFactory discountFactory,
            List<OrderObserver> observers) {

        this.menuService = menuService;
        this.tableService = tableService;
        this.discountFactory = discountFactory;
        this.observers = observers;
    }

    public RestrauntOrder createOrder(
            int tableNumber,
            String customerName,
            List<Long> menuItemIds,
            String specialInstructions) {

    	RestrauntTable table =
                tableService.getByTableNumber(tableNumber);

        if (table.isOccupied()) {
            throw new RuntimeException(
                    "Table is already occupied");
        }

        List<OrderItem> items = new ArrayList<>();

        for (Long menuItemId : menuItemIds) {

            MenuItem menuItem =
                    menuService.getById(menuItemId);

            items.add(
                    new OrderItem(menuItem, 1)
            );
        }

        Long orderId =
                idGenerator.getAndIncrement();

        // BUILDER PATTERN
        RestrauntOrder order =
                new RestrauntOrder.Builder()
                        .id(orderId)
                        .tableNumber(tableNumber)
                        .customerName(customerName)
                        .specialInstructions(
                                specialInstructions)
                        .items(items)
                        .build();

        orders.put(orderId, order);

        tableService.occupyTable(tableNumber);

        notifyObservers(order);

        return order;
    }

    public RestrauntOrder getOrder(Long id) {

    	RestrauntOrder order = orders.get(id);

        if (order == null) {
            throw new RuntimeException(
                    "Order not found");
        }

        return order;
    }

    public RestrauntOrder updateStatus(
            Long id,
            OrderStatus status) {

    	RestrauntOrder order =
                getOrder(id);

        order.setStatus(status);

        notifyObservers(order);

        if (status == OrderStatus.COMPLETED) {
            tableService.freeTable(
                    order.getTableNumber());
        }

        return order;
    }

    public double calculateBill(
            Long orderId,
            String discountType) {

    	RestrauntOrder order =
                getOrder(orderId);

        // STRATEGY PATTERN
        DiscountStrategy strategy =
                discountFactory.getStrategy(discountType);

        double subtotal =
                order.getSubtotal();

        double discount =
                strategy.calculateDiscount(subtotal);

        double discountedAmount =
                subtotal - discount;

        // DECORATOR PATTERN
        //
        // Start with basic amount.
        BillCalculator calculator =
                () -> discountedAmount;

        calculator =
                new ServiceChargeDecorator(calculator);

        calculator =
                new TaxDecorator(calculator);

        calculator =
                new PackingChargeDecorator(calculator);

        return calculator.calculate();
    }

    private void notifyObservers(
    		RestrauntOrder order) {

        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}