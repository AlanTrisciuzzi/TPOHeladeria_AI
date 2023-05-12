package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Freezer {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String description;
    private String marca;
    private Integer temperatura;

    @OneToMany(mappedBy = "freezer")
    private List<Helado> heladoList;

}
