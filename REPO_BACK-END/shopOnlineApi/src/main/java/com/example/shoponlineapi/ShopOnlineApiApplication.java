package com.example.shoponlineapi;

import com.example.shoponlineapi.service.impl.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ShopOnlineApiApplication {
//
//    @Autowired
//    EmailSendService senderService;

    public static void main(String[] args) {
        SpringApplication.run(ShopOnlineApiApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() {
//        senderService.sendEmail("huynhkimhieu1@gmail.com",
//                "This is email subject",
//                "This is email body");
//    }

}
