package com.restaurant.Restaurant.services;

import com.restaurant.Restaurant.models.Pedido;
import com.restaurant.Restaurant.models.User;
import com.restaurant.Restaurant.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserService userService;

    public Pedido findById(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException(
                "Pedido não encontrado! Id: " + id + ", Tipo " + Pedido.class.getName()));

    }

    @Transactional
    public Pedido create(Pedido obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
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
