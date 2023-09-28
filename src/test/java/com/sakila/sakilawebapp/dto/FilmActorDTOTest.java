package com.sakila.sakilawebapp.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmActorDTOTest {

    @Test
    void testFilmActorDTO() {
        FilmActorDTO filmActor = new FilmActorDTO();
        filmActor.setActorId((short) 1);
        filmActor.setFilmId((short) 10);
        filmActor.setLastUpdate(Date.valueOf("2023-01-01"));

        assertEquals((short) 1, filmActor.getActorId());
        assertEquals((short) 10, filmActor.getFilmId());
        assertEquals(Date.valueOf("2023-01-01"), filmActor.getLastUpdate());
    }
}
