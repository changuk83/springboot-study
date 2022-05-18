package com.app.messages;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message save(String text) {
        return this.messageRepository.saveMessage(new Message(text));
    }
}
