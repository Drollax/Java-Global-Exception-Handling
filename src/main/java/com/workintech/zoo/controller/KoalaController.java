package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Integer, Koala> koalas;

    public KoalaController(){
        koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public Collection<Koala> getAllKoalas(){
        return koalas.values();
    }

    @GetMapping("/koalas/{id}")
    public Koala getKoalaById(@PathVariable Integer id){
        if(!koalas.containsKey(id)){
            throw new RuntimeException("Not Found");
        }
        return koalas.get(id);
    }
    @PostMapping("/koalas")
    public Koala addKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);

        return koala;
    }

    @PutMapping("/koalas/{id}")
    public Koala updateKoala(@PathVariable Integer id, @RequestBody Koala koala){
        if(koalas.containsKey(id)){
            koalas.put(id, koala);
        }
        return koala;
    }

    @DeleteMapping("/koalas/{id}")
    public void deleteKoala(@PathVariable Integer id){
        if(koalas.containsKey(id)){
            koalas.remove(id);
        }
    }

}
