package com.sakila.sakilawebapp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class LanguageDTO {

    private Byte languageId;
    private String name;
    private Date lastUpdate;
}
