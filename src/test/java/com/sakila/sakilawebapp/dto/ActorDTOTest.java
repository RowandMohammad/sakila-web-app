package com.sakila.sakilawebapp.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActorDTOTest {

    @Test
    void testActorDTO() {
        ActorDTO actor = new ActorDTO();
        actor.setActorId((short) 1);
        actor.setFirstName("John");
        actor.setLastName("Doe");

        assertEquals((short) 1, actor.getActorId());
        assertEquals("John", actor.getFirstName());
        assertEquals("Doe", actor.getLastName());
        assertEquals(Date.valueOf(LocalDate.now()), actor.getLastUpdate());
    }
}
