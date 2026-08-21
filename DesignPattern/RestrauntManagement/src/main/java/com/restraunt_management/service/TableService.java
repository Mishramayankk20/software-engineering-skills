package com.restraunt_management.service;
import org.springframework.stereotype.Service;

import com.restraunt_management.model.RestrauntOrder;
import com.restraunt_management.model.RestrauntTable;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TableService {

    private final Map<Long, RestrauntTable> tables =
            new HashMap<>();

    private final AtomicLong idGenerator =
            new AtomicLong(1);

    public RestrauntTable createTable(int tableNumber) {

        Long id = idGenerator.getAndIncrement();

        RestrauntTable table =
                new RestrauntTable(id, tableNumber);

        tables.put(id, table);

        return table;
    }

    public List<RestrauntTable> getAll() {
        return new ArrayList<>(tables.values());
    }

    public RestrauntTable getByTableNumber(int tableNumber) {

        return tables.values()
                .stream()
                .filter(t -> t.getTableNumber() == tableNumber)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Table not found"));
    }

    public void occupyTable(int tableNumber) {

    	RestrauntTable table =
                getByTableNumber(tableNumber);

        table.setOccupied(true);
    }

    public void freeTable(int tableNumber) {

    	RestrauntTable table =
                getByTableNumber(tableNumber);

        table.setOccupied(false);
    }
}