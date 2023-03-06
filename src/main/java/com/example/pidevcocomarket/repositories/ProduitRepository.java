package com.example.pidevcocomarket.Repositories;

import com.example.pidevcocomarket.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {
}
