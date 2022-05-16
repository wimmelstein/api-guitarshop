package nl.inholland.apiguitarshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GuitarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuitarService guitarService;


    @Test
    void someting() throws Exception {
        this.mockMvc.perform(get("/guitars"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Fender")));
    }

    @Test
    void other() throws Exception {
        Guitar guitar = new Guitar();
        guitar.setBrand(new Brand("Bla"));
        guitar.setModel("Fender");
        GuitarDTO dto = new GuitarDTO();
        when(guitarService.createGuitar(any(GuitarDTO.class))).thenReturn(guitar);
        mockMvc.perform(post("/guitars")
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand.name").value("Bla"))
                .andExpect(jsonPath("$.model").value("Fender"));
    }


}