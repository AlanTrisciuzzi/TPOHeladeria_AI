package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreezerDTO {
    private Integer id;
    private String description;
    private String marca;
    private Integer temperatura;

    @OneToMany(mappedBy = "heladoDTO")
    @JsonIgnore
    private List<HeladoDTO> heladoList;

}
