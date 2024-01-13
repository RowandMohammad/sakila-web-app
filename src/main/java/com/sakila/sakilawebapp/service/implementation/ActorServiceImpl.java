package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.entity.Actor;
import com.sakila.sakilawebapp.exception.ResourceNotFoundException;
import com.sakila.sakilawebapp.repository.ActorRepository;
import com.sakila.sakilawebapp.repository.FilmActorRepository;
import com.sakila.sakilawebapp.service.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private final ActorRepository actorRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ActorServiceImpl(ActorRepository actorRepository, ModelMapper modelMapper) {
        this.actorRepository = actorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActorDTO> getAllActors() {
        return actorRepository.findAll().stream()
                .map(actor -> modelMapper.map(actor, ActorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO getActorById(Short id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
        return modelMapper.map(actor, ActorDTO.class);
    }

    @Override
    public ActorDTO createActor(ActorDTO actorDTO) {
        Actor actor = modelMapper.map(actorDTO, Actor.class);
        Actor savedActor = actorRepository.save(actor);
        return modelMapper.map(savedActor, ActorDTO.class);
    }

    @Override
    public ActorDTO updateActor(Short id, ActorDTO actorDTO) {
        Actor existingActor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
        existingActor.setFirstName(actorDTO.getFirstName());
        existingActor.setLastName(actorDTO.getLastName());
        Actor updatedActor = actorRepository.save(existingActor);
        return modelMapper.map(updatedActor, ActorDTO.class);
    }

    @Override
    public void deleteActor(Short id) {
        filmActorRepository.deleteByActorId(id);
        actorRepository.deleteById(id);
    }
}
