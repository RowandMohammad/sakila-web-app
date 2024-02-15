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
class ActorControllerTest {

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorService actorService;

    @Test
    void testGetAllActors() {
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



    @Test
    void testGetActorById() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Test");
        actorDTO.setLastName("Actor");

        when(actorService.getActorById((short) 1)).thenReturn(actorDTO);

        ResponseEntity<ActorDTO> response = actorController.getActorById((short) 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Test");
    }


    @Test
    void testCreateActor() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Test");
        actorDTO.setLastName("Actor");

        when(actorService.createActor(any(ActorDTO.class))).thenReturn(actorDTO);

        ResponseEntity<ActorDTO> response = actorController.createActor(actorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getFirstName()).isEqualTo("Test");
    }

    @Test
    void testUpdateActor() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Updated");
        actorDTO.setLastName("Actor");

        when(actorService.updateActor((short) 1, actorDTO)).thenReturn(actorDTO);

        ResponseEntity<ActorDTO> response = actorController.updateActor((short) 1, actorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Updated");
    }

    @Test
    void testDeleteActor() {
        ResponseEntity<Void> response = actorController.deleteActor((short) 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testSearchActorsWithQuery() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId((short) 1);
        actorDTO.setFirstName("Penelope");
        actorDTO.setLastName("Actor");

        when(actorService.searchActors("Penelope")).thenReturn(Arrays.asList(actorDTO));

        ResponseEntity<List<ActorDTO>> response = actorController.searchActors("Penelope");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(response.getBody().get(0).getFirstName()).isEqualTo("Penelope");
    }


    @Test
    void testSearchActorsWithEmptyQuery() {
        ActorDTO actorDTO1 = new ActorDTO();
        actorDTO1.setActorId((short) 1);
        actorDTO1.setFirstName("First");
        actorDTO1.setLastName("Actor");

        ActorDTO actorDTO2 = new ActorDTO();
        actorDTO2.setActorId((short) 2);
        actorDTO2.setFirstName("Second");
        actorDTO2.setLastName("Actor");

        when(actorService.getAllActors()).thenReturn(Arrays.asList(actorDTO1, actorDTO2));

        ResponseEntity<List<ActorDTO>> response = actorController.searchActors("");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(2);
    }





}
