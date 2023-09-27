package com.sakila.sakilawebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "film_actor")
@Data
@IdClass(FilmActorId.class)
public class FilmActor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "actor_id", nullable = false)
    private Short actorId;

    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @JsonIgnoreProperties("films")
    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id", insertable = false, updatable = false)
    private Actor actor;


    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}


