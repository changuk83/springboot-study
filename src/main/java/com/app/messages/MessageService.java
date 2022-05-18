package com.app.messages;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MessageService {
    private MessageRepositoryForOpenSession messageRepositoryForOpenSession;

    public MessageService(MessageRepositoryForOpenSession messageRepositoryForOpenSession) {
        this.messageRepositoryForOpenSession = messageRepositoryForOpenSession;
    }

    @Transactional
    public Message save(String text) {
        return this.messageRepositoryForOpenSession.saveMessage(new Message(text));
    }
}
