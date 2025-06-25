package com.cyclemate.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Integer age;
    private Double weight;
}
