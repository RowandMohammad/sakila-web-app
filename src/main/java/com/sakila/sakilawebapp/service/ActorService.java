package com.sakila.sakilawebapp.service;

import com.sakila.sakilawebapp.dto.ActorDTO;

import java.util.List;

public interface ActorService {

    List<ActorDTO> getAllActors();

    ActorDTO getActorById(Short id);

    ActorDTO createActor(ActorDTO actorDTO);

    ActorDTO updateActor(Short id, ActorDTO actorDTO);

    void deleteActor(Short id);
}
