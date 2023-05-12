package com.example.demo.services;

import com.example.demo.models.Freezer;
import com.example.demo.repository.FreezerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@Service
public class FreezerService {
    private final FreezerRepository fr;

    @Autowired
    public FreezerService(FreezerRepository fr){this.fr = fr;};


    public ResponseEntity addFreezer (Freezer freezer){
        try {
            fr.save(freezer);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public Freezer getFreezer(Integer id){
        return fr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Freezer no encontrado"));
    }

    public ArrayList<Freezer> getAll() {return (ArrayList<Freezer>) fr.findAll();}

    public ResponseEntity deleteFreezer(Integer id){
        try {
            fr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateFreezer(@NotNull Integer id, Freezer freezer){
        try {
            Freezer f = fr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Freezer no encontrado"));
            f.setId(freezer.getId());
            f.setDescription(freezer.getDescription());
            f.setMarca(freezer.getMarca());
            f.setTemperatura(freezer.getTemperatura());
            fr.save(f);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
         return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
