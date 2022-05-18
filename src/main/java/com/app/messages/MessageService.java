package com.app.messages;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(String text) {
        return this.messageRepository.saveMessage(new Message(text));
    }
}
