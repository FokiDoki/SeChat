package com.seChat.controllers;

import com.seChat.artifacts.User;
import com.seChat.mongoRepo.MessageRepository;
import com.seChat.mongoRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value= "/users/findManyByUsername")
    public List<User> GetAllUsers(Model model,
                                  @RequestParam(defaultValue  = ".") String search) {
        return userRepository.findAllByUsername(search);
    }



}