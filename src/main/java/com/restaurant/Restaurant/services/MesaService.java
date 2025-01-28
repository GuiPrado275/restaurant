package com.restaurant.Restaurant.services;

import com.restaurant.Restaurant.models.Mesa;
import com.restaurant.Restaurant.models.dto.MesaCreateDTO;
import com.restaurant.Restaurant.models.dto.MesaUpdateDTO;
import com.restaurant.Restaurant.repositories.PedidoRepository;
import com.restaurant.Restaurant.repositories.MesaRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class MesaService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public Mesa findById(Long id) {
        Optional<Mesa> mesa = this.mesaRepository.findById(id);
        return mesa.orElseThrow(() -> new RuntimeException(
                "Mesa não encontrada! Id: " + id + ", Tipo " + Mesa.class.getName()));

    }

    @Transactional //save in the bank
    public Mesa create(Mesa obj) {
        obj.setId(null);
        obj = this.mesaRepository.save(obj);
        this.pedidoRepository.saveAll(obj.getPedidos());
        return obj;
    }

    @Transactional
    public Mesa update(Mesa obj) {
        Mesa newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.mesaRepository.save(newObj);
    }

    public void delete(Long id) {
       findById(id);
       try{
           this.mesaRepository.deleteById(id);
       } catch (Exception e) {
           throw new RuntimeException("Impossível excluir pois há entidades relacionadas");
       }
    }

    public Mesa fromDTO(@Valid MesaCreateDTO obj) {
        Mesa mesa = new Mesa();
        mesa.setNumMesa(obj.getNumMesa());
        mesa.setPassword(obj.getPassword());
        return mesa;
    }

    public Mesa fromDTO(@Valid MesaUpdateDTO obj) {
        Mesa mesa = new Mesa();
        mesa.setId(obj.getId());
        mesa.setPassword(obj.getPassword());
        return mesa;
    }
}
