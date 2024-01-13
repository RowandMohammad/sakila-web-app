package com.sakila.sakilawebapp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmCategoryId implements Serializable {
    private Short filmId;

    private Byte categoryId;
}