package com.sakila.sakilawebapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "staff")
public class Staff implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", unique = true, nullable = false)
    private Byte staffId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "address_id", nullable = false)
    private Short addressId;


    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "store_id", nullable = false)
    private Byte storeId;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

}
