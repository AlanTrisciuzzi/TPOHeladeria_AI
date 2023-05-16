package com.example.demo.services;

import com.example.demo.models.Freezer;
import com.example.demo.models.FreezerDTO;
import com.example.demo.repository.FreezerRepository;
import com.example.demo.repository.HeladoRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class FreezerService {
    private final FreezerRepository fr;
    private final HeladoRepository hr;
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    public FreezerService(FreezerRepository fr, HeladoRepository hr){this.fr = fr; this.hr = hr};


    public ResponseEntity addFreezer (Freezer freezer){
        try {
            fr.save(freezer);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


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
            Freezer f = fr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Freezer no encontrado"));
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



    public FreezerDTO getFreezer(Integer id){
        Freezer f = fr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Freezer no encontrado"));
        return  mm.map(f, FreezerDTO.class);
    }

    public List<FreezerDTO> getAll() {
        List<Freezer> freezers = fr.findAll();
        List<FreezerDTO> freezersdto = new ArrayList<FreezerDTO>();
        for (Freezer f : freezers){
            freezersdto.add(mm.map(f, FreezerDTO.class));
        }
        return freezersdto;
    }


}
