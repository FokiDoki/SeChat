package com.seChat.controllers;

import com.seChat.artifacts.ERole;
import com.seChat.artifacts.Role;
import com.seChat.artifacts.User;
import com.seChat.mongoRepo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.*;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {
    private UserRepository userRepository;

    @Autowired
    RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value= "/signup")
    public String getPage(Model model) {
        return "signup";
    }

    @PostMapping(value="/signup")
    public ModelAndView register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String passwordAgain,
                                 HttpServletRequest request){
        if (userRepository.findUserByUsername(username)!=null) {
            return new ModelAndView("redirect:signup?exists");
        }
        Set<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        roles.add(role);
        if (!password.equals(passwordAgain)) {
            request.setAttribute("match", "1");
            return new ModelAndView("redirect:signup?match");
        }
        password = new BCryptPasswordEncoder().encode(password);
        PublicKey pubKey = null;
        PrivateKey pvtKey = null;
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            pubKey = kp.getPublic();
            pvtKey = kp.getPrivate();
        } catch (NoSuchAlgorithmException e){

        }
        userRepository.save(new User(new ObjectId(), username, password, 0, roles,
                new Binary(pubKey.getEncoded()),new Binary(pvtKey.getEncoded())));
        return new ModelAndView("redirect:login");
    }
}
