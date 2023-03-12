package com.project.template.controller;

import com.project.template.persistence.entity.UserEntity;
import com.project.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;


    @PostMapping
    public void addUser(UserEntity userEntity){
        userService.createUser(userEntity);
    }

    @GetMapping
    public List<UserEntity> getAll(@RequestParam String country){
        return userService.getUsersByCountry(country);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        userService.deleteUser(id);
    }
}
