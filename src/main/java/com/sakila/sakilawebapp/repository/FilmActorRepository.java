package com.sakila.sakilawebapp.repository;


import com.sakila.sakilawebapp.entity.FilmActor;
import com.sakila.sakilawebapp.entity.FilmActorId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

    @Transactional
    @Modifying
    @Query("DELETE FROM FilmActor fa WHERE fa.filmId = :filmId")
    void deleteByFilmId(@Param("filmId") Short filmId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FilmActor fa WHERE fa.actorId = :actorId")
    void deleteByActorId(@Param("actorId") Short actorId);
}


