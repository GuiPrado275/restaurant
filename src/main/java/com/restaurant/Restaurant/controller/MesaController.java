package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.models.Mesa;
import com.restaurant.Restaurant.models.dto.MesaCreateDTO;
import com.restaurant.Restaurant.models.dto.MesaUpdateDTO;
import com.restaurant.Restaurant.services.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mesa")
@Validated
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> findById(@PathVariable Long id) {
        Mesa obj = this.mesaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> create(@Valid @RequestBody MesaCreateDTO obj) {
        Mesa mesa = this.mesaService.fromDTO(obj);
        Mesa newMesa = this.mesaService.create(mesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newMesa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> update(@Valid @RequestBody MesaUpdateDTO obj, @PathVariable Long id) {
        obj.setId(id);
        Mesa mesa = this.mesaService.fromDTO(obj);
        this.mesaService.update(mesa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
