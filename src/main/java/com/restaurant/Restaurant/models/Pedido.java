package com.restaurant.Restaurant.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Pedido.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    public static final String TABLE_NAME = "pedidos";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id is random
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotBlank
    private String description;

}
