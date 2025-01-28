package com.restaurant.Restaurant.repositories;

import com.restaurant.Restaurant.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    //Mesa findBynumMesa(String numMesa); - this isn't necessary because the "Jpa Repository" has many methods

    @Transactional(readOnly = true)
    Mesa findBynumMesa(String numMesa); //For search mesa for numMesa

}
