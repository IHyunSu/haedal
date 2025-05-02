package org.hyunsu.controller;

import org.hyunsu.domain.User;
import org.hyunsu.dto.UserRegistrationRequestDto;
import org.hyunsu.dto.UserSimpleResponseDto;
import org.hyunsu.service.UserService;
//패키지명이 다를 시 본인 패키지명으로 작성해야 오류가 안납니다.

import org.springframework.beans.factory.annotation.Autowired; //생성자를 통한 주입
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final org.hyunsu.service.UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}