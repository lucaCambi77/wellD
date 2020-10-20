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

import static org.hamcrest.CoreMatchers.notNullValue;
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(point)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.point", notNullValue()))
                .andReturn();
    }

    @Test
    public void should_find_all_points() throws Exception {
        mockMvc.perform(get("/wellD/space/")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_find_all_lines() throws Exception {
        mockMvc.perform(get("/wellD/lines/2")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_delete_all_point() throws Exception {
        mockMvc.perform(delete("/wellD/space")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
