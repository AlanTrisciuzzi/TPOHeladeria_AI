package com.example.demo.services;


import com.example.demo.models.Helado;
import com.example.demo.repository.HeladoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class HeladoService {
    private final HeladoRepository hr;

    @Autowired
    private HeladoService (HeladoRepository hr){this.hr = hr;}


    public ResponseEntity addHelado (Helado helado){
        try {
            hr.save(helado);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public Helado getHelado(Integer id){
        return hr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Helado no encontrado"));
    }

    public ArrayList<Helado> getAll() {return (ArrayList<Helado>) hr.findAll();}

    public ResponseEntity deleteHelado(Integer id){
        try {
            hr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateHelado(@NotNull Integer id, Helado helado){
        try {
            Helado h = hr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Helado no encontrado"));
            h.setId(helado.getId());
            h.setName(helado.getName());
            h.setCategoria(helado.getCategoria());
            h.setStock(helado.getStock());
            hr.save(h);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


}
