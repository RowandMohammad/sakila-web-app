package com.sakila.sakilawebapp.repository;


import com.sakila.sakilawebapp.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Short> {
}
