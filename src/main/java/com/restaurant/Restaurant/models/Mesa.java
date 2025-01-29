package com.restaurant.Restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restaurant.Restaurant.models.enums.ProfileEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = Mesa.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

    public static final String TABLE_NAME = "mesa"; // table name in database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_mesa", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String numMesa;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mesa_perfil", joinColumns = @JoinColumn(name = "mesa_id"))
    @Column(name = "perfil", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> mesas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pedido> pedidos = new HashSet<>();

    public Set<ProfileEnum> getProfiles() {
        return this.mesas.stream()
                .map(ProfileEnum::toEnum)
                .collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum) {
        this.mesas.add(profileEnum.getCode());
    }
}

