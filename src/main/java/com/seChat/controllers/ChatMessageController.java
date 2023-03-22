package com.seChat.controllers;

import com.seChat.artifacts.Message;
import com.seChat.artifacts.User;
import com.seChat.mongoRepo.MessageRepository;
import com.seChat.mongoRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatMessageController {
    MessageRepository messageRepository;
    UserRepository userRepository;
    User currentUser;

    @Autowired
    public ChatMessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value= "/message/send")
    public void sendMessage(Model model,
                          @RequestParam String message,
                          @RequestParam String toUsername) {
        currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message newMessage = new Message(message,
                currentUser.getUsername(),
                toUsername,

                System.currentTimeMillis());
        messageRepository.save(newMessage);
    }

    @GetMapping(value= "/message/getFrom")
    public List<Message> GetMessages(Model model,
                                     @RequestParam String withUsername, @RequestParam long timeShift) {
        currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> e =  messageRepository.getAllWithUserTimestampSlice(withUsername,
                currentUser.getUsername(),
                timeShift
                );
        return e;
    }
}
