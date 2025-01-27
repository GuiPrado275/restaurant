package com.restaurant.Restaurant.repositories;

import com.restaurant.Restaurant.models.Pedido;
import com.restaurant.Restaurant.models.projection.PedidoPorjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<PedidoPorjection> findByUser_Id(Long id); //search id

}
