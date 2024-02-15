package com.sakila.sakilawebapp.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LanguageDTOTest {

    @Test
    void testLanguageDTO() {
        LanguageDTO language = new LanguageDTO();
        language.setLanguageId((byte) 3);
        language.setName("English");
        language.setLastUpdate(Date.valueOf("2023-01-01"));

        assertEquals((byte) 3, language.getLanguageId());
        assertEquals("English", language.getName());
        assertEquals(Date.valueOf("2023-01-01"), language.getLastUpdate());
    }
}
