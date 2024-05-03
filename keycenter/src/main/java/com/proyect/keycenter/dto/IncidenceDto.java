package com.proyect.keycenter.dto;

import lombok.Data;

import com.proyect.keycenter.modelo.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class IncidenceDto {
    private long id;
    private String topic;
    private String description;
    private Timestamp send_date;
    private int state;
    private int user_id;

    public IncidenceDto() {
    }

    public IncidenceDto(Incidence incidence) {
        id = incidence.getId();
        topic = incidence.getTopic();
        description = incidence.getDescription();
        send_date = incidence.getSend_date();
        state = incidence.getState();
        user_id = incidence.getUser_id();
    }
}
