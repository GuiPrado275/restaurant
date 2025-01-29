package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.models.Pedido;
import com.restaurant.Restaurant.models.projection.PedidoProjection;
import com.restaurant.Restaurant.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Validated
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = pedidoService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<List<PedidoProjection>> findAllByMesa(@PathVariable Long mesaId) {
        List<PedidoProjection> objs = this.pedidoService.findAllByMesa(mesaId);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    @Transactional
    public ResponseEntity<Void> create(@Valid @RequestBody Pedido obj) {
        Pedido createdPedido = this.pedidoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdPedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    @Transactional
    public ResponseEntity<Void> update(@Valid @RequestBody Pedido obj, @PathVariable Long id) {
        obj.setId(id);
        this.pedidoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

