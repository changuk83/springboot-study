package com.app.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "text", nullable = false, length = 128)
    private String text;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Message(String text) {
        this.text = text;
        this.createdDate = new Date();
    }
}
