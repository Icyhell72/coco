package com.example.pidevcocomarket.Repositories;

import com.example.pidevcocomarket.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {
}
