package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        List<ActorDTO> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable Short id) {
        ActorDTO actor = actorService.getActorById(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody ActorDTO actorDTO) {
        ActorDTO createdActor = actorService.createActor(actorDTO);
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable Short id, @RequestBody ActorDTO actorDTO) {
        ActorDTO updatedActor = actorService.updateActor(id, actorDTO);
        return new ResponseEntity<>(updatedActor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ActorDTO>> searchActors(@RequestParam String query) {
        if (!StringUtils.hasLength(query)) {
            return getAllActors();
        }
        List<ActorDTO> actors = actorService.searchActors(query);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
}