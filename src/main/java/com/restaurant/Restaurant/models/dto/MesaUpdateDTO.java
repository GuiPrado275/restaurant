package com.restaurant.Restaurant.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MesaUpdateDTO {

    private Long id;

    @NotBlank
    @Size(min = 8, max = 60)
    private String password;
}
