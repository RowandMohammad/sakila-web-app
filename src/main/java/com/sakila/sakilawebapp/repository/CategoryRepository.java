package com.sakila.sakilawebapp.repository;

import com.sakila.sakilawebapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
