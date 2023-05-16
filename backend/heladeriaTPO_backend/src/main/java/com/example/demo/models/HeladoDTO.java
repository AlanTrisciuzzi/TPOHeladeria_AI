package com.example.demo.models;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeladoDTO {
    private Integer id;
    private String name;
    private String categoria;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "freezer_id")
    private FreezerDTO freezer;
}
