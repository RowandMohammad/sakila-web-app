
package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "inventory", catalog = "sakila")
public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", unique = true, nullable = false)
    private Integer inventoryId;

    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Column(name = "store_id", nullable = false)
    private Byte storeId;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

