package com.restraunt_management.controller;

import org.springframework.web.bind.annotation.*;


import com.restraunt_management.model.MenuItem;
import com.restraunt_management.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public MenuItem create(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price) {

        return menuService.create(
                name,
                category,
                price
        );
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return menuService.getAll();
    }
}