package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "film_category")
@IdClass(FilmCategoryId.class)
public class FilmCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", unique = true, nullable = false)
    private Short filmId;

    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    private Byte categoryId;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
