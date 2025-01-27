package com.restaurant.Restaurant.services;

import com.restaurant.Restaurant.models.User;
import com.restaurant.Restaurant.repositories.PedidoRepository;
import com.restaurant.Restaurant.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo " + User.class.getName()));

    }

    @Transactional //save in the bank
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.pedidoRepository.saveAll(obj.getPedidos());
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
       findById(id);
       try{
           this.userRepository.deleteById(id);
       } catch (Exception e) {
           throw new RuntimeException("Impossível excluir pois há entidades relacionadas");
       }
    }

}
