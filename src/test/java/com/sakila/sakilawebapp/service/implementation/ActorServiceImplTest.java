package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.entity.Actor;
import com.sakila.sakilawebapp.exception.ResourceNotFoundException;
import com.sakila.sakilawebapp.repository.ActorRepository;
import com.sakila.sakilawebapp.repository.FilmActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ActorServiceImplTest {

    @InjectMocks
    private ActorServiceImpl actorService;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private FilmActorRepository filmActorRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActors() {
        Actor actor = new Actor();
        when(actorRepository.findAll()).thenReturn(Collections.singletonList(actor));
        when(modelMapper.map(actor, ActorDTO.class)).thenReturn(new ActorDTO());

        assertNotNull(actorService.getAllActors());
        verify(actorRepository).findAll();
    }

    @Test
    void testGetActorById() {
        Actor actor = new Actor();
        when(actorRepository.findById((short) 1)).thenReturn(Optional.of(actor));
        when(modelMapper.map(actor, ActorDTO.class)).thenReturn(new ActorDTO());

        assertNotNull(actorService.getActorById((short) 1));
        verify(actorRepository).findById((short) 1);
    }

    @Test
    void testGetActorByIdNotFound() {
        when(actorRepository.findById((short) 1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> actorService.getActorById((short) 1));
    }

    @Test
    void testCreateActor() {
        ActorDTO dto = new ActorDTO();
        Actor actor = new Actor();
        when(modelMapper.map(dto, Actor.class)).thenReturn(actor);
        when(actorRepository.save(actor)).thenReturn(actor);
        when(modelMapper.map(actor, ActorDTO.class)).thenReturn(dto);

        assertNotNull(actorService.createActor(dto));
    }

    @Test
    void testUpdateActor() {
        ActorDTO dto = new ActorDTO();
        Actor actor = new Actor();
        when(actorRepository.findById((short) 1)).thenReturn(Optional.of(actor));
        when(actorRepository.save(actor)).thenReturn(actor);
        when(modelMapper.map(actor, ActorDTO.class)).thenReturn(dto);

        assertNotNull(actorService.updateActor((short) 1, dto));
        verify(actorRepository).findById((short) 1);
    }

    @Test
    void testUpdateActorNotFound() {
        ActorDTO dto = new ActorDTO();
        when(actorRepository.findById((short) 1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> actorService.updateActor((short) 1, dto));
    }



    @Test
    void testSearchActors() {
        Actor actor = new Actor();
        when(actorRepository.findByFirstNameContainingOrLastNameContaining("query", "query")).thenReturn(Collections.singletonList(actor));
        when(modelMapper.map(actor, ActorDTO.class)).thenReturn(new ActorDTO());

        assertNotNull(actorService.searchActors("query"));
        verify(actorRepository).findByFirstNameContainingOrLastNameContaining("query", "query");
    }
}
