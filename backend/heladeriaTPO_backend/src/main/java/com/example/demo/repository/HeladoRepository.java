package com.example.demo.repository;

import com.example.demo.models.Helado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeladoRepository extends JpaRepository<Helado, Integer> { }
