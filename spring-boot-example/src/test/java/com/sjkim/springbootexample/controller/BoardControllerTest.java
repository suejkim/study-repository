package com.sjkim.springbootexample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjkim.springbootexample.dto.BoardSaveDto;
import com.sjkim.springbootexample.persistence.board.BoardRepository;
import com.sjkim.springbootexample.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoardService boardService;
    @MockBean
    private BoardRepository boardRepository;


//    @BeforeEach
//    void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @Test
    void getBoardList() throws Exception {
        mockMvc.perform(get("/board")
                .param("title", "TI")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void addBoard() throws Exception {
        var saveDto = BoardSaveDto.builder().title(UUID.randomUUID().toString())
                .content(UUID.randomUUID().toString())
                .writer("TEST").build();
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveDto)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateBoard() throws Exception {
        var saveDto = BoardSaveDto.builder().content(UUID.randomUUID().toString()).build();
        mockMvc.perform(put("/board/{id}".replace("{id}", "27"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveDto)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void deleteBoard() throws Exception {
        mockMvc.perform(delete("/board/{id}".replace("{id}", "27")))
                .andExpect(status().isOk()).andDo(print());
    }
}