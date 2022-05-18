package com.app.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepositoryForOpenSession {
    private final SessionFactory sessionFactory;



    public Message saveMessage(Message message) {
        log.info("Saved message : {}", message.getText());

        // Datasource 로부터 새로운 DB 커넥션을 가져온다.
        // 이전의 Transaction 과 연결이 되지 않고 따로 동작.
        Session session = sessionFactory.openSession();
        session.save(message);
        return message;
    }
}
