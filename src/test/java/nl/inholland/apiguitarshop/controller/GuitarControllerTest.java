package nl.inholland.apiguitarshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.apiguitarshop.annotation.WithAdminUser;
import nl.inholland.apiguitarshop.config.TestConfig;
import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GuitarController.class)
@Import(GuitarController.class)
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class GuitarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuitarService guitarService;


    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    @WithMockUser(roles = {"USER"})
    void getAllGuitarsShouldReturnJsonArrayOfSizeOne() throws Exception {
        when(guitarService.getAllGuitars()).thenReturn(List.of(new Guitar(new Brand("Fender"), "Jazz", 1500)));
        mockMvc.perform(get("/guitars/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand.name").value("Fender"));
    }

    @Test
    @WithAdminUser
    void createGuitarWithRoleAdminShouldReturnStatusCreatedAndOneObject() throws Exception {
        Guitar guitar = new Guitar();
        guitar.setBrand(new Brand("Fender"));
        guitar.setModel("Cougar");
        GuitarDTO dto = new GuitarDTO();
        when(guitarService.createGuitar(any(GuitarDTO.class))).thenReturn(guitar);
        mockMvc.perform(post("/guitars")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand.name").value("Fender"))
                .andExpect(jsonPath("$.model").value("Cougar"));
    }

}

