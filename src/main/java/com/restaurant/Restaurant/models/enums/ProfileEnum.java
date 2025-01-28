package com.restaurant.Restaurant.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ProfileEnum {

    ADMIN(1, "ROLE_ADMIN"),
    MESA(2, "ROLE_MESA");

    private Integer code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {

        if(Objects.isNull(code)){
            return null;
        }
        for (ProfileEnum x : ProfileEnum.values()) {
            if (x.getCode().equals(code)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Code " + code);

    }

}
