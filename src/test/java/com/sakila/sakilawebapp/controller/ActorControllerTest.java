package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.SakilaWebApplication;
import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.service.ActorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SakilaWebApplication.class)
@AutoConfigureMockMvc
public class ActorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
    }

    @Test
    public void getAllActors_ShouldReturnActors() throws Exception {
        ActorDTO actor1 = new ActorDTO();
        actor1.setActorId((short)1);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");

        when(actorService.getAllActors()).thenReturn(Arrays.asList(actor1));

        mockMvc.perform(get("/actors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actorId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));

    }

    @Test
    public void getActorById_ShouldReturnActor() throws Exception {
        ActorDTO actor = new ActorDTO();
        actor.setActorId((short)2);
        actor.setFirstName("Jane");
        actor.setLastName("Smith");

        when(actorService.getActorById((short)2)).thenReturn(actor);

        mockMvc.perform(get("/actors/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actorId").value(2))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    public void createActor_ShouldReturnCreatedActor() throws Exception {
        ActorDTO actor = new ActorDTO();
        actor.setFirstName("Jane");
        actor.setLastName("Smith");

        ActorDTO savedActor = new ActorDTO();
        savedActor.setActorId((short)3);
        savedActor.setFirstName("Jane");
        savedActor.setLastName("Smith");

        when(actorService.createActor(any(ActorDTO.class))).thenReturn(savedActor);

        mockMvc.perform(post("/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Jane\",\"lastName\":\"Smith\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actorId").value(3));
    }

}

