package org.hyunsu.dto;

import lombok.Getter;

@Getter
public class UserRegistrationRequestDto {
    private String username;
    private String password;
    private String name;
}