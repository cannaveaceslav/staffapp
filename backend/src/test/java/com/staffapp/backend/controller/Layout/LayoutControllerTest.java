package com.staffapp.backend.controller.Layout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.service.layout.LayoutService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LayoutControllerTest {

  @Mock
  private LayoutService layoutService;
  @InjectMocks
  private LayoutController layoutController;
  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMvc;
  private Random random = new Random();


  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
            .standaloneSetup(layoutController)
            .build();
  }

  @Test
  @SneakyThrows
  void saveLocationReturns201() {
    final Location testLocation = Location.builder()
            .id((long) random.nextInt(1000))
            .locationNumber((long) random.nextInt(1000))
            .available(true)
            .description("Location created for Unit tests")
            .employee(null)
            .createdAt(LocalDateTime.now())
            .pos_x(400.0)
            .pos_y(200.0)
            .build();


    mockMvc.perform(post("/layout/save")
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testLocation)))
            .andDo(print())
            .andExpect(jsonPath("$.statusCode").value(201))
            .andExpect(status().isOk());
  }

  @Test
  void getLocationsReturns201() throws Exception {
    mockMvc.perform(get("/layout"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON));
  }

  @Test
  @SneakyThrows
  void getOneLocationReturns201() {
    int locationId = 25;
    mockMvc.perform(get("/layout/get/{id}", locationId))
            .andExpect(jsonPath("$.message").value("Location with id " + locationId + " retrieved"))
            .andExpect(status().isOk());
  }

}