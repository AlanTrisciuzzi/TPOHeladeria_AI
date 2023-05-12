package com.example.demo.controller;

import com.example.demo.models.Freezer;
import com.example.demo.services.FreezerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freezer")
@CrossOrigin(origins = "*")
public class FreezerController {
    @Autowired
    private FreezerService fs;
    @PostMapping("/addFreezer")
    public ResponseEntity addFreezer (@RequestBody final @NotNull Freezer f) {
        return fs.addFreezer(f);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updateFreezer (@RequestBody final @NotNull Integer id, @RequestBody final @NotNull Freezer freezer){
        return updateFreezer(id, freezer);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteFreezer(@PathVariable final @NotNull Integer id){
        return deleteFreezer(id);
    }

    @GetMapping("/getFreezer")
    public Freezer getFreezer(@PathVariable final @NotNull Integer id) {
        return fs.getFreezer(id);
    }

    @GetMapping("/getAll")
    public List<Freezer> getAll(){
        return fs.getAll();
    }

}