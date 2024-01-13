package com.sakila.sakilawebapp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FilmActorDTO {

    private Short actorId;
    private Short filmId;
    private Date lastUpdate;
}