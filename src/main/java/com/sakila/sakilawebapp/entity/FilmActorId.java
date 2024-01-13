package com.sakila.sakilawebapp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmActorId implements Serializable {
    private Short actorId;
    private Short filmId;
}