package com.app.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/hello")
    public ResponseEntity<Message> test() {
        Message saved = messageService.save("Hello");
        if(saved == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(saved);
    }
}
