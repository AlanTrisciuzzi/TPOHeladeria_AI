package com.example.demo.controller;

import com.example.demo.models.Helado;
import com.example.demo.models.HeladoDTO;
import com.example.demo.services.HeladoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helado")
@CrossOrigin(origins = "*")
public class HeladoController {
    @Autowired
    private HeladoService hs;

    @PostMapping("/addHelado")
    public ResponseEntity addHelado (@RequestBody final @NotNull Helado h) {
        return hs.addHelado(h);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updateHelado (@PathVariable final @NotNull Integer id, @RequestBody final @NotNull Helado helado){
        return hs.updateHelado(id, helado);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteHelado(@PathVariable final @NotNull Integer id){
        return hs.deleteHelado(id);
    }

    @GetMapping("/getHelado")
    public HeladoDTO getHelado(@PathVariable final @NotNull Integer id) {
        return hs.getHelado(id);
    }

    @GetMapping("/getAll")
    public List<HeladoDTO> getAll(){
        return hs.getAll();
    }

}
