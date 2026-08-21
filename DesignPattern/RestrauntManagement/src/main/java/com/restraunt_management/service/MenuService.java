package com.restraunt_management.service;
import org.springframework.stereotype.Service;

import com.restraunt_management.factory.MenuItemFactory;
import com.restraunt_management.model.MenuItem;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MenuService {

    private final Map<Long, MenuItem> menuItems =
            new HashMap<>();

    private final AtomicLong idGenerator =
            new AtomicLong(1);

    private final MenuItemFactory factory;

    public MenuService(MenuItemFactory factory) {
        this.factory = factory;
    }

    public MenuItem create(
            String name,
            String category,
            double price) {

        Long id = idGenerator.getAndIncrement();

        MenuItem item =
                factory.create(
                        id,
                        name,
                        category,
                        price
                );

        menuItems.put(id, item);

        return item;
    }

    public List<MenuItem> getAll() {
        return new ArrayList<>(menuItems.values());
    }

    public MenuItem getById(Long id) {

        MenuItem item = menuItems.get(id);

        if (item == null) {
            throw new RuntimeException(
                    "Menu item not found");
        }

        return item;
    }
}