package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.service.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorControllerTest {

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorService actorService;

    @Test
    public void testGetAllActors() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Test");
        actorDTO.setLastName("Actor");

        when(actorService.getAllActors()).thenReturn(Arrays.asList(actorDTO));

        ResponseEntity<List<ActorDTO>> response = actorController.getAllActors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(response.getBody().get(0).getFirstName()).isEqualTo("Test");
    }

    // Similarly, provide tests for other endpoints.
    // Example for getActorById:

    @Test
    public void testGetActorById() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Test");
        actorDTO.setLastName("Actor");

        when(actorService.getActorById((short) 1)).thenReturn(actorDTO);

        ResponseEntity<ActorDTO> response = actorController.getActorById((short) 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Test");
    }

    // You can continue in this manner for createActor, updateActor, deleteActor, and searchActors.

    @Test
    public void testCreateActor() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Test");
        actorDTO.setLastName("Actor");

        when(actorService.createActor(any(ActorDTO.class))).thenReturn(actorDTO);

        ResponseEntity<ActorDTO> response = actorController.createActor(actorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getFirstName()).isEqualTo("Test");
    }

    // And so on for other methods...

}
