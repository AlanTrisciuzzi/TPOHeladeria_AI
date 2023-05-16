package com.example.demo.services;


import com.example.demo.models.Helado;
import com.example.demo.models.HeladoDTO;
import com.example.demo.repository.HeladoRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class HeladoService {
    private final HeladoRepository hr;
    private final ModelMapper mm = new ModelMapper();

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

    public HeladoDTO getHelado(int id){
        Helado h = hr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Helado no encontrado"));
        return mm.map(h, HeladoDTO.class);
    }

    public List<HeladoDTO> getAll(){
        List<Helado> helados = hr.findAll();
        List<HeladoDTO> heladodto = new ArrayList<HeladoDTO>();
        for (Helado h : helados){
            heladodto.add(mm.map(h,HeladoDTO.class));
        }
        return heladodto;
    }

}
