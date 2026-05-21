package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    public KangarooController() {
        kangaroos = new HashMap<>();
    }

    @GetMapping("/kangaroos")
    public Collection<Kangaroo> getAllKangaroo() {
        return kangaroos.values();
    }

    @GetMapping("/kangaroos/{id}")
    public Kangaroo getKangarooById(@PathVariable Integer id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Not found", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping("/kangaroos")
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null || kangaroo.getName() == null) {
            throw new ZooException("Kangaroo ID and Name cannot be null", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/kangaroos/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        if (kangaroos.containsKey(id)) {
            kangaroos.put(id, kangaroo);
        }
        return kangaroo;
    }

    @DeleteMapping("/kangaroos/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id) {
        Kangaroo removed = kangaroos.remove(id);

        if (removed == null) {
            throw new RuntimeException("Not found");
        }

        return removed;
    }
}
