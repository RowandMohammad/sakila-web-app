package com.sakila.sakilawebapp.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;


@Data
public class ActorDTO {

    private Short actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate = Date.valueOf(LocalDate.now());
}

