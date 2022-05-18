package com.app.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepository {
    private final SessionFactory sessionFactory;



    public Message saveMessage(Message message) {
        log.info("Saved message : {}", message.getText());
        Session session = sessionFactory.openSession();
        session.save(message);
        return message;
    }
}
