package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Helado {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private String categoria;
    private Integer stock;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "freezer_id")
    private List<Freezer> freezer;

}
