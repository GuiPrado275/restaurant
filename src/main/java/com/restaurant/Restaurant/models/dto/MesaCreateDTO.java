package com.restaurant.Restaurant.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MesaCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String numMesa;

    @NotBlank
    @Size(min = 8, max = 60)
    private String password;

}
