package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "language")
@Data
public class Language implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", unique = true, nullable = false)
    private Byte languageId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "language")
    private List<Film> films;
}
