package com.restaurant.Restaurant.services;

import com.restaurant.Restaurant.models.Pedido;
import com.restaurant.Restaurant.models.Mesa;
import com.restaurant.Restaurant.models.projection.PedidoProjection;
import com.restaurant.Restaurant.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaService mesaService;

    public Pedido findById(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException(
                "Pedido não encontrado! Id: " + id + ", Tipo " + Pedido.class.getName()));
    }

    public List<PedidoProjection> findAllByMesa(Long mesaId) {
        Mesa mesa = mesaService.findById(mesaId);
        return this.pedidoRepository.findByMesa_Id(mesaId);
    }

    @Transactional
    public Pedido create(Pedido obj) {
        Mesa mesa = this.mesaService.findById(obj.getMesa().getId());
        obj.setId(null);
        obj.setMesa(mesa);

        Iterator<Pedido> iterator = mesa.getPedidos().iterator();
        while (iterator.hasNext()) {
            Pedido p = iterator.next();
        }

        mesa.getPedidos().add(obj);
        this.mesaService.update(mesa);

        return this.pedidoRepository.save(obj);
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

        try {
            this.pedidoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Impossível excluir pedido com entidades relacionadas.");
        }
    }
}
