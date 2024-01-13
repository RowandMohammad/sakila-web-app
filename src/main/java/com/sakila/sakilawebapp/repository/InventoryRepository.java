
package com.sakila.sakilawebapp.repository;

import com.sakila.sakilawebapp.entity.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Short> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.filmId = :filmId")
    void deleteByFilmId(@Param("filmId") Short filmId);



}

