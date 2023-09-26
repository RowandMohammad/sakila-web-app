package com.sakila.sakilawebapp.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StaffDTO {

    private Byte staffId;
    private String firstName;
    private String lastName;
    private Short addressId;
    private String email;
    private Byte storeId;
    private Boolean active;
    private String username;
    private String password;
    private Timestamp lastUpdate;

}
