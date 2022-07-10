package com.popov.registration.service.entity.developer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String name;
    private String password;
}
