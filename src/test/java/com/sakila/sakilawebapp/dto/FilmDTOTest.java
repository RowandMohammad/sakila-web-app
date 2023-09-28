package com.sakila.sakilawebapp.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmDTOTest {

    @Test
    void testFilmDTO() {
        FilmDTO film = new FilmDTO();
        film.setFilmId((short) 5);
        film.setTitle("Test Movie");
        film.setDescription("A test movie description");
        film.setReleaseYear(2023);
        film.setLanguageId((byte) 2);

        assertEquals((short) 5, film.getFilmId());
        assertEquals("Test Movie", film.getTitle());
        assertEquals("A test movie description", film.getDescription());
        assertEquals(2023, film.getReleaseYear());
        assertEquals((byte) 2, film.getLanguageId());
        assertEquals(Date.valueOf(java.time.LocalDate.now()), film.getLastUpdate());
    }
}
