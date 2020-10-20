package com.cambi.wellD.controller;

import com.cambi.wellD.WellDApplication;
import com.cambi.wellD.model.Point;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {WellDApplication.class})
@AutoConfigureMockMvc
public class WellDControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    @Test
    public void should_add_point() throws Exception {
        Point point = new Point(1.0, 2.0);

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(point)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.x", is(1.0)))
                .andExpect(jsonPath("$.y", is(2.0)))
                .andReturn();
    }

    @Test
    public void should_find_all_points() throws Exception {
        mockMvc.perform(get("/wellD/space/")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.space").isArray())
                .andExpect(jsonPath("$.space", hasSize(0)))
                .andReturn();

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(1.0, 2.0))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(mediaType))
                .andReturn();

        mockMvc.perform(get("/wellD/space/")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.space").isArray())
                .andExpect(jsonPath("$.space", hasSize(1)))
                .andReturn();
    }

    @Test
    public void should_find_all_lines() throws Exception {

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(0.0, 1.0))))
                .andReturn();

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(1.0, 2.0))))
                .andReturn();

        mockMvc.perform(get("/wellD/lines/2")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.segments").isMap())
                .andExpect(jsonPath("$.segments", hasKey("Y=X+1.0")))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_delete_all_point() throws Exception {

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(1.0, 2.0))))
                .andReturn();

        mockMvc.perform(get("/wellD/space/")
                .contentType(mediaType))
                .andExpect(jsonPath("$.space").isArray())
                .andExpect(jsonPath("$.space", hasSize(1)))
                .andReturn();

        mockMvc.perform(delete("/wellD/space")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.space").isArray())
                .andExpect(jsonPath("$.space", hasSize(0)))
                .andReturn();
    }

    @Test
    public void should_fail_for_invalid_input() throws Exception {

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(mediaType))
                .andDo(print())
                .andReturn();

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(null, 1.0))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(mediaType))
                .andDo(print())
                .andReturn();

        mockMvc.perform(post("/wellD/point")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(new Point(1.0, null))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(mediaType))
                .andDo(print())
                .andReturn();
    }
}
