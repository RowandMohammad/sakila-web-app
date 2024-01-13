package com.sakila.sakilawebapp.repository;

import com.sakila.sakilawebapp.entity.FilmCategory;
import com.sakila.sakilawebapp.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

    List<FilmCategory> findAllByCategoryId(Byte categoryId);


}
