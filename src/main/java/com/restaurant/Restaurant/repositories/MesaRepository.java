package com.restaurant.Restaurant.repositories;

import com.restaurant.Restaurant.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    @Transactional(readOnly = true)
    Mesa findBynumMesa(String numMesa); // Método que você já tem para encontrar por numMesa

    @Transactional(readOnly = true)
    @Query("SELECT m FROM Mesa m LEFT JOIN FETCH m.pedidos WHERE m.id = :id")
    Mesa findByIdWithPedidos(Long id); // Novo método para buscar a mesa com seus pedidos
}

