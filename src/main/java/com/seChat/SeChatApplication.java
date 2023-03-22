package com.seChat;

import com.seChat.controllers.ChatController;
import com.seChat.utils.ApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SeChatApplication {
    static ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SeChatApplication.class, args);
        context = ApplicationContextProvider.getApplicationContext();
        ChatController chat = context.getBean(ChatController.class);
    }


}
