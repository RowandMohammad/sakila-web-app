package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "actor")
public class Actor implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", unique = true, nullable = false)
    private Short actorId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "last_update", length = 19)
    private Date lastUpdate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "actor")
    private List<FilmActor> filmActors;

}