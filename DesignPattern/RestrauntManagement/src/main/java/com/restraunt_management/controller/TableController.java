package com.restraunt_management.controller;

import org.springframework.web.bind.annotation.*;

import com.restraunt_management.model.RestrauntTable;
import com.restraunt_management.service.TableService;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public RestrauntTable create(
            @RequestParam int tableNumber) {

        return tableService.createTable(tableNumber);
    }

    @GetMapping
    public List<RestrauntTable> getAll() {
        return tableService.getAll();
    }
}
