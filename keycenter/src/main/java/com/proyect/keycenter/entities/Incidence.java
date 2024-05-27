package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "incidence")
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String topic;
    private String description;
    private Timestamp send_date;
    private int state;
    private int user_id;

    public Incidence() {
    }

    public Incidence(String topic, String description, int user_id) {
        this.topic = topic;
        this.description = description;
        this.send_date = new Timestamp(new Date().getTime());
        this.state = 0;
        this.user_id = user_id;
    }

}
