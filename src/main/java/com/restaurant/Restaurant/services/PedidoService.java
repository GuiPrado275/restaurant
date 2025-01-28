package com.restaurant.Restaurant.services;

import com.restaurant.Restaurant.models.Pedido;
import com.restaurant.Restaurant.models.Mesa;
import com.restaurant.Restaurant.models.projection.PedidoPorjection;
import com.restaurant.Restaurant.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaService mesaService;

    private Mesa mesa;

    public Pedido findById(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException(
                "Pedido não encontrado! Id: " + id + ", Tipo " + Pedido.class.getName()));

    }


    public List<PedidoPorjection> findAllByMesa() {
        List<PedidoPorjection> pedidos = this.pedidoRepository.findByMesa_Id(mesa.getId());//find the tasks
        return pedidos;
    }


    @Transactional
    public Pedido create(Pedido obj) {
        Mesa mesa = this.mesaService.findById(obj.getMesa().getId());
        obj.setId(null);
        obj.setMesa(mesa);
        obj = this.pedidoRepository.save(obj);
        return obj;
    }

    @Transactional
    public Pedido update(Pedido obj) {
        Pedido newObj = this.findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.pedidoRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try{
            this.pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Impossível excluir pois há entidades relacionadas");
        }
    }
}
