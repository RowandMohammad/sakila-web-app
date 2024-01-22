package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private Byte categoryId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
