package com.sakila.sakilawebapp.repository;

import com.sakila.sakilawebapp.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LanguageRepository extends JpaRepository<Language, Byte> {
}
