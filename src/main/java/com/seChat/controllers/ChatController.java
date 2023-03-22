package com.seChat.controllers;

import com.seChat.artifacts.User;
import com.seChat.mongoRepo.UserRepository;
import com.seChat.utils.CookieTools;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    UserRepository userRepository;

    @Autowired
    public ChatController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value= "/chat")
    public String getChat(Model model, HttpServletResponse response,
                          @RequestParam(required = false) String user) {
        User messagingWithUser = null;
        User me = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        me.setLastSeen(System.currentTimeMillis());
        userRepository.save(me);
        if (user!=null) {
            messagingWithUser = userRepository.findUserByUsername(user);
            if (messagingWithUser!=null) {
                response.addCookie(CookieTools.getSecureCookie("PvtKey", messagingWithUser.getPvtKeyBase64()));
            }
        }
        response.addCookie(CookieTools.getSecureCookie("MyPubKey", me.getPubKeyBase64()));
        response.addCookie(CookieTools.getSecureCookie("MyPvtKey", me.getPvtKeyBase64()));
        model.addAttribute("user", messagingWithUser);
        model.addAttribute("Users", userRepository.findAllByUsername("."));
        return "chat";
    }
}