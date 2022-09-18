package com.example.flight.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class WebUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
