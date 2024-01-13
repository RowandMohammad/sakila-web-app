package com.sakila.sakilawebapp.repository;


import com.sakila.sakilawebapp.entity.Film;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM rental WHERE inventory_id IN (SELECT inventory_id FROM inventory WHERE film_id=:filmId)", nativeQuery = true)
    void deleteRelatedRentals(@Param("filmId") Short filmId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM film_actor WHERE film_id=:filmId", nativeQuery = true)
    void deleteRelatedFilmActors(@Param("filmId") Short filmId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM inventory WHERE film_id=:filmId", nativeQuery = true)
    void deleteRelatedInventory(@Param("filmId") Short filmId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM film_category WHERE film_id=:filmId", nativeQuery = true)
    void deleteRelatedFilmCategory(@Param("filmId") Short filmId);

}
